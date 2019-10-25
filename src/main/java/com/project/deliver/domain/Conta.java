package com.project.deliver.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Builder
@Data
@Entity
@Table(name = "Conta")
public class Conta {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "VALOR_ORIGINAL")
    private BigDecimal valorOriginal;
    @Column(name = "VALOR_CORRIGIDO")
    private BigDecimal valorCorrigido;
    @Column(name = "VENCIMENTO")
    private LocalDate vencimento;
    @Column(name = "PAGAMENTO")
    private LocalDate pagamento;
    @Column(name = "DIAS_ATRASO")
    private Integer diasAtraso;

    @ManyToOne
    @JoinColumn(name = "ATRASO", columnDefinition = "ID")
    private Atraso atraso;
}
