package com.erp.kicksotck.responsedtos;

import com.erp.kicksotck.entities.Empresa;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmpresaResponseDTO(
        UUID id,
        String nomeEmpresa,
        String cnpj,
        String email,
        String token,
        LocalDateTime created_at
) {
    public EmpresaResponseDTO(Empresa empresa, String token){
        this(
                empresa.getId(),
                empresa.getNome_empresa(),
                empresa.getCnpj_empresa(),
                empresa.getEmail_empresa(),
                token,
                empresa.getCreated_at()
        );
    }
}
