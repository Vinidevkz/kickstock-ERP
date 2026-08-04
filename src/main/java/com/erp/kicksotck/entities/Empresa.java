package com.erp.kicksotck.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_empresas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome_empresa;
    @Column(nullable = false, unique = true)
    private String email_empresa;
    @Column(nullable = false, length = 60)
    private String password_empresa;
    @Column(nullable = false, unique = true)
    private String cnpj_empresa;
    @OneToMany(mappedBy = "id_empresa")
    private List<Contrato> contratos;
    private LocalDateTime created_at;


}
