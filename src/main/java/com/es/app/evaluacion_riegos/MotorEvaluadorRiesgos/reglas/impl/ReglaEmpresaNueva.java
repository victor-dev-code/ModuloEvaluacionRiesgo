package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;
import com.es.app.evaluacion_riegos.providers.VerificacionLegalProvider;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ReglaEmpresaNueva implements IReglaEvaluacion{
    private final VerificacionLegalProvider verificacionLegal;

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();

        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_EMPRESA_NUEVA);

        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("La empresa tiene 18 o menos meses de existencia");

        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("La empresa tiene más de 18 meses de existencia");
        }

        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        var datosLegalesEmpresa = verificacionLegal.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId());
        return datosLegalesEmpresa.getFechaCreacion().isAfter(
            LocalDate.now().minusMonths(MotorConstantes.MESES_DE_EXISTENCIA));
    }
}
