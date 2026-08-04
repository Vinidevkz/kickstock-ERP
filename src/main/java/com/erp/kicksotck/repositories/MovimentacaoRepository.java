package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao, UUID> {
}
