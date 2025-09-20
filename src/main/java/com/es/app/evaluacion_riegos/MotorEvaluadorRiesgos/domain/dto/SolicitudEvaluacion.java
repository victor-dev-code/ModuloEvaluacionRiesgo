package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.ProductoFinanciero;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudEvaluacion {
    private String empresaId;
    private BigDecimal montoSolicitado;
    private ProductoFinanciero productoFinanciero;
    private LocalDate fechaSolicitud;
}
