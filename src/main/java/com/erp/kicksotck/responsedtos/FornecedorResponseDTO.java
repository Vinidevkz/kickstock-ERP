package com.erp.kicksotck.responsedtos;

import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;

import java.time.LocalDateTime;
import java.util.UUID;

public record FornecedorResponseDTO (
        UUID id,
        String nomeFornecedor,
        String cnpj,
        String email,
        String token,
        LocalDateTime created_at
) {
public FornecedorResponseDTO(Fornecedor fornecedor, String token){
    this(
            fornecedor.getId(),
            fornecedor.getNome_fornecedor(),
            fornecedor.getCnpj_fornecedor(),
            fornecedor.getEmail(),
            token,
            fornecedor.getCreated_at()
    );
}
}
