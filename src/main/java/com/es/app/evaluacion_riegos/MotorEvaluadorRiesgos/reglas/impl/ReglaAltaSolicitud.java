package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;
import com.es.app.evaluacion_riegos.providers.DatosContablesProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReglaAltaSolicitud implements IReglaEvaluacion{
    private final  DatosContablesProvider datosContables;

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();
        
        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("El monto solicitado es 8 veces mayor al promedio mensual de ventas");
        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("El monto solicitado NO es 8 veces mayor al promedio de ventas al mes");
        }

        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        var datosContablesEmpresa = datosContables.obtenerDatosContables(solicitudEvaluacion.getEmpresaId());

        BigDecimal limitePrestamo = 
            datosContablesEmpresa.getIngresosMensuales().multiply(BigDecimal.valueOf(MotorConstantes.MONTO_ALTA_SOLICITUD));

        return solicitudEvaluacion.getMontoSolicitado().compareTo(limitePrestamo) > 0;
    }    
}
