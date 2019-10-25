package com.project.deliver.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class AtrasoDTO implements Serializable {
    private static final long serialVersionUID = 3378671214771553555L;

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
