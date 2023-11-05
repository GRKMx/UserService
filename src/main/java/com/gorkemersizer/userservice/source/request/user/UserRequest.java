package com.gorkemersizer.userservice.source.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserRequest {

    @Id
    @NotBlank
    private String id;
    @NotBlank
    @Size(max = 20)
    private String name;
    @NotBlank
    @Size(max = 20)
    private String surname;
    @NotBlank
    @Size(max = 20)
    private String email;
    @NotBlank
    @Size(max = 20)
    private String countryId;
    @NotBlank
    @Size(max = 20)
    private Integer balance;
}
