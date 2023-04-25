package com.cdc.producerappcdc.cdc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaCdcListener {

    private final ObjectMapper objectMapper;
    private final AccountRepository accountRepository;

    @KafkaListener(
            topics = "dbserver1.testdb.accounts",
            groupId = "group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listenWithHeaders(@Payload String string) throws JsonProcessingException {

        /**
         * String Payload를 객체 인스턴스로 변환.
         */
        AccountDto accountDto = objectMapper.readValue(string, AccountDto.class);
        Account account = new Account(
                accountDto.getPayload().getAccount_id(),
                accountDto.getPayload().getRole_id(),
                accountDto.getPayload().getUser_name(),
                accountDto.getPayload().getUser_description(),
                accountDto.getPayload().getUpdate_date()
        );

        /**
         * 몽고 데이터베이스에 저장
         */
        accountRepository.save(account);
    }
}
