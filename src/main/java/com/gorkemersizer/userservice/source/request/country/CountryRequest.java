package com.gorkemersizer.userservice.source.request.country;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryRequest {

    @NotBlank
    private String id;
}
