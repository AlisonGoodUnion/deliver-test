package com.project.deliver.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Atraso")
public class Atraso {

    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "DIAS_ATRASO")
    private String diasAtraso;
    @Column(name = "MULTA")
    private String multa;
    @Column(name = "JUROS_DIA")
    private BigDecimal juros;
}
