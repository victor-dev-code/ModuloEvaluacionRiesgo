package com.es.app.evaluacion_riegos.providers.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialPagosEntity {
    private String empresaId;
    private int totalPagosRealizados;
    private int totalMesesAtrasados;
    private int totalDiasAtrasados;
    private boolean conPagosAtrasadosUltimos12Meses;
    private boolean enProcesoRefinanciamiento;
}
