package com.erp.kicksotck.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_vendas_e_compras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendasECompras {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa id_empresa;
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor id_fornecedor;
    private String nome_produto;
    private String descricao_produto;
    private LocalDateTime data_e_hora_da_compra;



}
