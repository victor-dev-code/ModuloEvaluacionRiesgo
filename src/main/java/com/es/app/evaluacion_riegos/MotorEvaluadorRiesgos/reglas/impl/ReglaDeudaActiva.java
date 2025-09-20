package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;
import com.es.app.evaluacion_riegos.providers.HistorialPagosProvider;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReglaDeudaActiva implements IReglaEvaluacion {
    private final HistorialPagosProvider historialPagos;

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();
        
        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_DEUDA_ACTIVA);
        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("La empresa posee una deuda vencida de más de 90 días");
        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("La regla cuenta con un buen historial de pagos");
        }

        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        var historialPagosEmpresa = historialPagos.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId());
        return historialPagosEmpresa.getTotalDiasAtrasados() > MotorConstantes.DIAS_ATRASO 
            || historialPagosEmpresa.getTotalMesesAtrasados() >= MotorConstantes.MESES_ATRASADOS;
    }  
}
