package com.cdc.producerappcdc.cdc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@NoArgsConstructor
@ToString
public class Account {

    @Id
    private String accountId;
    private String roleId;
    private String userName;
    private String userDescription;
    private String updateDate;

    public Account(String accountId, String roleId, String userName, String userDescription, String updateDate) {
        this.accountId = accountId;
        this.roleId = roleId;
        this.userName = userName;
        this.userDescription = userDescription;
        this.updateDate = updateDate;
    }
}
