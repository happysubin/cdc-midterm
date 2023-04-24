package com.data.cdc.mysql;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MySQLCustomerRepository extends JpaRepository<Customer, Long> {
}
