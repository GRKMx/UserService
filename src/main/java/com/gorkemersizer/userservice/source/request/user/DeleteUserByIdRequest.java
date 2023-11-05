package com.gorkemersizer.userservice.source.request.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeleteUserByIdRequest {

    @NotBlank
    private String id;
}
