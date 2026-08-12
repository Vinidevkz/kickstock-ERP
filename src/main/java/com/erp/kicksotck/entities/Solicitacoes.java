package com.erp.kicksotck.entities;

import com.erp.kicksotck.enums.Status;
import com.erp.kicksotck.enums.TipoSolicitacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_solicitacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "empresa")
    private Empresa id_empresa;
    @ManyToOne
    @JoinColumn(name = "fornecedor")
    private Fornecedor id_fornecedor;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_solicitacao")
    private TipoSolicitacao tipo_solicitacao;
    private Integer quantidade_lote;
    @OneToMany
    @JoinTable(
            name = "tb_solicitacoes_lote",
            joinColumns = @JoinColumn(name = "id_solicitacoes"),
            inverseJoinColumns = @JoinColumn(name = "id_lote")
    )
    private List<Lote> lotes = new ArrayList<>();
    private LocalDate data_compra;
    @Enumerated(EnumType.STRING)
    private Status status;
    private LocalDateTime created_at;

}
