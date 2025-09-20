package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.service;

import java.util.Set;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.NivelRiesgo;

public interface MotorRiesgosService {
    ResultadoEvaluacion evaluarRiesgo(SolicitudEvaluacion solicitudEvaluacion);
    ResultadoEvaluacion determinarNivelRiesgo(ResultadoEvaluacion resultadoFinal, Set<String> reglasCumplidas);
    ResultadoEvaluacion aplicarResultado(ResultadoEvaluacion resultado, NivelRiesgo nivel, String motivoFinal);
}
