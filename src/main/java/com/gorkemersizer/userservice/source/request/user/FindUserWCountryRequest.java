package com.gorkemersizer.userservice.source.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindUserWCountryRequest {

    @NotBlank
    private String id;
}
