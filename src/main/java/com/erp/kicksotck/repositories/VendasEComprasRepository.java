package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.VendasECompras;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VendasEComprasRepository extends JpaRepository<VendasECompras, UUID> {
}
