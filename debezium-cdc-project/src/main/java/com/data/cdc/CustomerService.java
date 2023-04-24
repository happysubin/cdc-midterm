package com.data.cdc;

import com.data.cdc.domain.Customer;
import com.data.cdc.domain.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.debezium.data.Envelope.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public void replicateData(Map<String, Object> customerData, Operation operation) {
        final ObjectMapper mapper = new ObjectMapper();
        final Customer customer = mapper.convertValue(customerData, Customer.class);

        log.info("몽고 디비 시작 {}", customer.toString());
        if (Operation.DELETE == operation) {
            customerRepository.deleteById(customer.getId());
        }
        else {
            customerRepository.save(customer);
        }
    }
}
