package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;

public interface IReglaEvaluacion {
    ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion);
    boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion);
}
