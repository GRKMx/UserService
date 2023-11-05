package com.gorkemersizer.userservice.source.response.user;

import lombok.Data;

@Data
public class UserResponse {

    private String name;
    private String surname;
    private String email;
    private String countryId;
    private Integer balance;
}
