package com.data.cdc;

import com.data.cdc.mysql.Customer;
import com.data.cdc.mysql.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
class CdcApplicationTests {
	
	@Autowired
    CustomerRepository customerRepository;

	@Test
	@Rollback(value = false)
	@Transactional(readOnly = false)
	void insertCustomer() {
		for (Long i = 0L; i < 3L; i++) {
			Customer customer = new Customer(i, "email" + i, "email " + i);
			customerRepository.save(customer);
		}
	}
}
