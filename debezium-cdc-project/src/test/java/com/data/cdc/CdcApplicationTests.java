package com.data.cdc;

import com.data.cdc.domain.Customer;
import com.data.cdc.domain.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CdcApplicationTests {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	void contextLoads() {
		List<Customer> all = customerRepository.findAll();
		for (Customer customer : all) {
			System.out.println("customer.toString() = " + customer.toString());
		}
	}
}
