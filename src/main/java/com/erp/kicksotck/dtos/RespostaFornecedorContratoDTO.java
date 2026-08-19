package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RespostaFornecedorContratoDTO(
        @NotNull(message = "O campo 'id_contrato' não pode estar vazio.") UUID id_contrato,
        @NotNull(message = "O campo 'tipo_aceitacao' não pode estar vazio.") String tipo_aceitacao
){}
