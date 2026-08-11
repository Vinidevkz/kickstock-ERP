package com.erp.kicksotck.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record ContratoDTO(
        @JsonProperty("id_fornecedor") @NotNull(message = "O campo 'id_fornecedor' não pode estar vazio") UUID id_fornecedor,
        @JsonProperty("data_encerramento") @NotNull(message = "O campo 'data_encerramento' não pode estar vazio.") LocalDate data_encerramento
){}