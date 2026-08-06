package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;

public record ContratoDTO(
        @NotBlank(message = "O campo 'id_empresa' não pode estar vazio") String id_empresa,
        @NotBlank(message = "O campo 'id_fornecedor' não pode estar vazio") String id_fornecedor,
        @NotBlank(message = "O campo 'data_encerramento' não pode estar vazio.") LocalDate data_encerramento
){}