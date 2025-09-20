package com.es.app.evaluacion_riegos.providers.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DatosContablesEntity {
    private String empresaId;
    private BigDecimal ingresosMensuales;
    private BigDecimal ingresosAnuales;
    private BigDecimal activosTotales;
    private BigDecimal pasivosTotales;
}
