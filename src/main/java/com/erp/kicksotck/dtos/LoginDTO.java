package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank(message = "O campo 'email' não pode estar vazio.") String email,
        @NotBlank(message = "O campo 'password' não pode estar vazio.") String password
){
}
