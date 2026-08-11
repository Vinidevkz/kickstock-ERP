package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Solicitacoes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SolicitacaoRepository extends JpaRepository<Solicitacoes, UUID> {
}
