package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotBlank;

public record FornecedorDTO(
        @NotBlank(message = "O campo 'nome_fornecedor' não pode estar vazio.") String nome_fornecedor,
        @NotBlank(message = "O campo 'email_fornecedor' não pode estar vazio.") String email_fornecedor,
        @NotBlank(message = "O campo 'password_fornecedor' não pode estar vazio.") String password_fornecedor,
        @NotBlank(message = "O campo 'cnpj_fornecedor' não pode estar vazio.") String cnpj_fornecedor
){}