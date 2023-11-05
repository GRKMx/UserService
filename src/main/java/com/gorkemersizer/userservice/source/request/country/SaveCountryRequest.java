package com.gorkemersizer.userservice.source.request.country;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder
public class SaveCountryRequest {

    @Id
    @NotBlank
    private String id;
    @NotBlank
    @Size(max = 20)
    private String countryCode;
    @NotBlank
    @Size(max = 20)
    private String currency;
}
