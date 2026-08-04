package com.erp.kicksotck.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_fornecedores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String nome_fornecedor;
    @Column(nullable = false, unique = true)
    private String email_fornecedor;
    @Column(nullable = false, length = 60)
    private String password_fornecedor;
    @Column(nullable = false, unique = true)
    private String cnpj_fornecedor;
    @OneToMany(mappedBy = "id_fornecedor")
    private List<Contrato> contratos;
    private LocalDateTime created_at;

}
