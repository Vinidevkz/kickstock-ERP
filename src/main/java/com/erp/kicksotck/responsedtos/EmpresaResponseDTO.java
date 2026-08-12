package com.erp.kicksotck.responsedtos;

import com.erp.kicksotck.entities.Empresa;

import java.time.LocalDateTime;
import java.util.UUID;

public record EmpresaResponseDTO(
        UUID id,
        String nomeEmpresa,
        String email,
        String cnpj,
        String token,
        LocalDateTime created_at
) {
    public EmpresaResponseDTO(Empresa empresa, String token){
        this(
                empresa.getId(),
                empresa.getNome_empresa(),
                empresa.getEmail(),
                empresa.getCnpj_empresa(),
                token,
                empresa.getCreated_at()
        );
    }
}
