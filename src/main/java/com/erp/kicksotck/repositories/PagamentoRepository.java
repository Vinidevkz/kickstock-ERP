package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoRepository extends JpaRepository<Pagamento, UUID> {
}
