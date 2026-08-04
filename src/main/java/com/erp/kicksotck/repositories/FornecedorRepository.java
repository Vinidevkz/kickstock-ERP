package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorRepository extends JpaRepository<Fornecedor, UUID> {
}
