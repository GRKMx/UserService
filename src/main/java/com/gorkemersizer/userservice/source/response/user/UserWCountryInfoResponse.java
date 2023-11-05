package com.gorkemersizer.userservice.source.response.user;

import lombok.Data;

@Data
public class UserWCountryInfoResponse {

    private String name;
    private String surname;
    private String email;
    private String countryCode;
    private String currency;
    private Integer balance;

}
