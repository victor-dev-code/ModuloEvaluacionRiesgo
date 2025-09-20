package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;
import com.es.app.evaluacion_riegos.providers.VerificacionLegalProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReglaDemandaLegal implements IReglaEvaluacion {
    private final VerificacionLegalProvider verificacionLegal;

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();
        
        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_DEMANDA_LEGAL);
        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("Existe un juicio mercantil en curso");
        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("No tiene juicios pendientes");
        }
        
        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        var datosLegalesEmpresa = verificacionLegal.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId());
        return datosLegalesEmpresa.getNumeroDemandasActivas() >= MotorConstantes.DEMANDA_ACTIVA;
    }
}
