package com.cdc.producerappcdc.cdc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AccountDto {

    private Payload payload;

    @Getter
    @NoArgsConstructor
    @ToString
    static class Payload{
        private String account_id;
        private String role_id;
        private String user_name;
        private String user_description;
        private String update_date;

        public Payload(String account_id,
                        String role_id,
                        String user_name,
                        String user_description,
                        String update_date) {
            this.account_id = account_id;
            this.role_id = role_id;
            this.user_name = user_name;
            this.user_description = user_description;
            this.update_date = update_date;
        }
    }

    public AccountDto(Payload payload) {
        this.payload = payload;
    }
}
