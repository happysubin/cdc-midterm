package com.data.cdc;

import com.data.cdc.mysql.CustomerService;
import io.debezium.data.Envelope;
import io.debezium.engine.RecordChangeEvent;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Map;

import static io.debezium.data.Envelope.FieldName.*;
import static java.util.stream.Collectors.toMap;

@Component
@RequiredArgsConstructor
public class CdcService {

    private final EntityManager em;
    private final CustomerService customerService;

    @Transactional(readOnly = false)
    public void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
        SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();
        Struct sourceRecordChangeValue= (Struct) sourceRecord.value();

        if (sourceRecordChangeValue != null) {

            if(sourceRecordChangeValue.schema().field("databaseName") != null){
                if(sourceRecordChangeValue.get("databaseName").equals(""))return; //""
            }

            if(sourceRecordChangeValue.schema().field("ddl") != null){
                Object ddl = sourceRecordChangeValue.get("ddl");
                String ddlString = String.valueOf(ddl);
                em.createNativeQuery(ddlString).executeUpdate();
                return;
            }

            Envelope.Operation operation = Envelope.Operation.forCode((String) sourceRecordChangeValue.get(OPERATION));

            if(operation != Envelope.Operation.READ) {
                String record = operation == Envelope.Operation.DELETE ? BEFORE : AFTER;
                Struct struct = (Struct) sourceRecordChangeValue.get(record);
                Map<String, Object> payload = struct
                        .schema()
                        .fields()
                        .stream()
                        .map(field -> field.name())
                        .filter(fieldName -> struct.get(fieldName) != null)
                        .map(fieldName -> Pair.of(fieldName, struct.get(fieldName)))
                        .collect(toMap(Pair::getKey, Pair::getValue));

                this.customerService.replicateData(payload, operation); //cdc 데이터 저장
            }
        }
    }
}
