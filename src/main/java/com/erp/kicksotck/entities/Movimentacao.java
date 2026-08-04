package com.erp.kicksotck.entities;

import com.erp.kicksotck.enums.TiposMovimentacoes;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_movimentacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movimentacao{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_lote")
    private Lote lote;
    private TiposMovimentacoes tipo_movimentacao;
    private LocalDateTime data_e_hora_movimentacao;

}
