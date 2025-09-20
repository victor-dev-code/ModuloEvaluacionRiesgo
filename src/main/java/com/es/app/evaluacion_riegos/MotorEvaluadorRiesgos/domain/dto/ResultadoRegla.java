package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoRegla {
    private String nombreRegla;
    private boolean estaCumplida;
    private String resultadoObtenido;
}
