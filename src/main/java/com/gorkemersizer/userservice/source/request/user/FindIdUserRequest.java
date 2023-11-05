package com.gorkemersizer.userservice.source.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FindIdUserRequest {

    @NotBlank
    private String id;
}
