package com.erp.kicksotck.dtos;

import com.erp.kicksotck.enums.TipoSolicitacao;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record SolicitacaoDTO(
        @NotNull(message = "O campo 'id_empresa' não pode estar vazio.") UUID idEmpresa,
        @NotNull(message = "O campo 'id_fornecedor' não pode estar vazio.") UUID idFornecedor,
        @NotNull(message = "O campo 'tipo_solicitacao' não pode estar vazio.") String tipo_solicitacao,
        @NotNull(message = "O campo 'quantidade_lote' não pode estar vazio.")
        @NotNull(message = "A lista com os id's dos lotes não pode estar vazia.") List<UUID> idsLotes,
        @NotNull(message = "O campo 'data_compra' não pode estar vazio.") LocalDate data_compra
){}
