package com.erp.kicksotck.entities;

import com.erp.kicksotck.enums.StatusSolicitacao;
import com.erp.kicksotck.enums.TipoSolicitacao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
    private TipoSolicitacao tipoSolicitacao;
    private StatusSolicitacao statusSolicitacao;
    private LocalDateTime created_at;

}
