package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Contrato;
import com.erp.kicksotck.entities.Empresa;
import com.erp.kicksotck.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ContratoRepository extends JpaRepository<Contrato, UUID> {
    @Query("SELECT COUNT(c) > 0 FROM Contrato c WHERE c.id_empresa = :empresa AND c.id_fornecedor = :fornecedor")
    boolean existsByEmpresaEFornecedor(
            @Param("empresa") Empresa empresa,
            @Param("fornecedor") Fornecedor fornecedor
    );
}
