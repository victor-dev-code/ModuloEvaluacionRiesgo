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
public class ReglaHistorialExcelente implements IReglaEvaluacion{
    private final HistorialPagosProvider historialPagos;

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();

        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);

        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("Los últimos 12 pagos fueron a tiempo y sin refinanciamiento");
        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("Empresa con pagos atrasados en los últimos 12 meses o refinanciamiento activo");
        }
        
        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        var historialPagosEmpresa = historialPagos.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId());
        return !historialPagosEmpresa.isConPagosAtrasadosUltimos12Meses() && !historialPagosEmpresa.isEnProcesoRefinanciamiento();
    }
}
