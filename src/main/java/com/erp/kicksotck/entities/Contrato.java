package com.erp.kicksotck.entities;

import com.erp.kicksotck.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tb_contratos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_empresa")
    @JsonIgnore
    private Empresa id_empresa;
    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    @JsonIgnore
    private Fornecedor id_fornecedor;
    private LocalDate data_encerramento;
    @Enumerated(EnumType.STRING)
    @Column(name = "status_contrato")
    private Status status_contrato;
    private String codigo_contrato;
    private LocalDateTime created_at;


}
