package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Contrato;
import com.erp.kicksotck.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface LoteRepository extends JpaRepository<Lote, UUID> {

    @Query("SELECT l FROM Lote l WHERE l.id_empresa.id = :idEmpresa")
    List<Lote> findAllByIdEmpresa(@Param("idEmpresa") UUID idEmpresa);

}
