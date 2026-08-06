package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ContratoDTO(
        @NotBlank(message = "O campo 'email_empresa' não pode estar vazio") String email_empresa,
        @NotBlank(message = "O campo 'email_fornecedor' não pode estar vazio") String email_fornecedor,
        @NotBlank(message = "O campo 'data_encerramento' não pode estar vazio.") LocalDate data_encerramento
        ){}
