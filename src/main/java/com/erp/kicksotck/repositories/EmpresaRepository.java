package com.erp.kicksotck.repositories;

import com.erp.kicksotck.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {

    Optional<Empresa> findByEmail_empresa(String email);

}
