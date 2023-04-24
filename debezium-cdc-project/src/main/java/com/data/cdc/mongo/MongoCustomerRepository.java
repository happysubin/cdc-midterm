package com.data.cdc.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoCustomerRepository extends MongoRepository<Customer, Long> {
}
