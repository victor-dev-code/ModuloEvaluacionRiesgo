package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.ProductoFinanciero;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;

@Component
public class ReglaProductoEstricto implements IReglaEvaluacion{

    @Override
    public ResultadoRegla aplicarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoRegla resultadoRegla = new ResultadoRegla();
        
        resultadoRegla.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);

        if (verificarRegla(solicitudEvaluacion)) {
            resultadoRegla.setEstaCumplida(true);
            resultadoRegla.setResultadoObtenido("El producto solicitado cuenta con Arrendamiento Financiero");
        } else {
            resultadoRegla.setEstaCumplida(false);
            resultadoRegla.setResultadoObtenido("El producto solicitado NO cuenta con Arrendamiento Financiero");
        }

        return resultadoRegla;
    }

    @Override
    public boolean verificarRegla(SolicitudEvaluacion solicitudEvaluacion) {
        return solicitudEvaluacion.getProductoFinanciero() == ProductoFinanciero.ARRENDAMIENTO_FINANCIERO;
    }
}
