package com.erp.kicksotck.dtos;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RespostaFornecedorSolicitacaoLoteDTO(
        @NotNull(message = "O campo 'id_solicitacao' não pode estar vazio.") UUID id_solicitacao,
        @NotNull(message = "O campo 'tipo_aceitacao' não pode estar vazio.") String tipo_aceitacao
){}
