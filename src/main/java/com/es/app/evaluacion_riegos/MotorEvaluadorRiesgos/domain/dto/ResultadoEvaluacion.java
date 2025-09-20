package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.NivelRiesgo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoEvaluacion {
    private String empresaId;
    private NivelRiesgo nivelRiesgo;
    private List<ResultadoRegla> reglasEvaluadas = new ArrayList<>();
    private String motivoFinal;

    public void agregarResultadoRegla(ResultadoRegla regla) {
        this.reglasEvaluadas.add(regla);
    }
}
