package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Lote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LoteRepository extends JpaRepository<Lote, UUID> {
}
