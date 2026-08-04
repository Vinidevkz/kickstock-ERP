package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotBlank;

public record EmpresaDTO(
        @NotBlank(message = "O campo 'nome_empresa' não pode estar vazio.") String nome_empresa,
        @NotBlank(message = "O campo 'email_empresa' não pode estar vazio.") String email_empresa,
        @NotBlank(message = "O campo 'password_empresa' não pode estar vazio.") String password_empresa,
        @NotBlank(message = "O campo 'cnpj_empresa' não pode estar vazio.") String cnpj_empresa
){}
