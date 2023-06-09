package com.data.cdc.mysql;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class Customer {

    @Id
    private Long id;
    private String fullname;
    private String email;

    public Customer(Long id, String fullname, String email) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
    }

    public Customer() {}

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

