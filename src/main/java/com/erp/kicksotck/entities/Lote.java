package com.erp.kicksotck.entities;

import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_lotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lote {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private Fornecedor fornecedor;
    private String nome_produto;
    private String descricao_produto;
    private LocalDate validade_produto;
    private Integer quantidade_por_lote;
    private String codigo_lote;
    private LocalDateTime created_at;

}
