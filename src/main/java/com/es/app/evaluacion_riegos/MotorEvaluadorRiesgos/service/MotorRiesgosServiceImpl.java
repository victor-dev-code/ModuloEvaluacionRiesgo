package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.NivelRiesgo;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;

@Service
public class MotorRiesgosServiceImpl implements MotorRiesgosService {
    private final List<IReglaEvaluacion> reglas;

    

    public MotorRiesgosServiceImpl(List<IReglaEvaluacion> reglas) {
        this.reglas = reglas;
    }

    @Override
    public ResultadoEvaluacion evaluarRiesgo(SolicitudEvaluacion solicitudEvaluacion) {
        ResultadoEvaluacion resultadoFinal = new ResultadoEvaluacion();
        Set<String> reglasCumplidas = new HashSet<>();
        resultadoFinal.setEmpresaId(solicitudEvaluacion.getEmpresaId());

        for (IReglaEvaluacion regla : reglas) {
            
            ResultadoRegla reglaAplicada = regla.aplicarRegla(solicitudEvaluacion);
            resultadoFinal.agregarResultadoRegla(reglaAplicada);

            if (reglaAplicada.isEstaCumplida()) {
                reglasCumplidas.add(reglaAplicada.getNombreRegla());
            }
            
        }

        return determinarNivelRiesgo(resultadoFinal, reglasCumplidas);
        
    }

    @Override
    public ResultadoEvaluacion determinarNivelRiesgo(ResultadoEvaluacion resultadoFinal, Set<String> reglasCumplidas) {
        boolean deudaActivaCumplida = reglasCumplidas.contains(MotorConstantes.REGLA_DEUDA_ACTIVA);
        boolean altaSolicitudCumplida = reglasCumplidas.contains(MotorConstantes.REGLA_ALTA_SOLICITUD);
        boolean demandaLegalCumplida = reglasCumplidas.contains(MotorConstantes.REGLA_DEMANDA_LEGAL);
        boolean empresaNuevaCumplida = reglasCumplidas.contains(MotorConstantes.REGLA_EMPRESA_NUEVA);
        boolean historialExcelenteCumplido = reglasCumplidas.contains(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        boolean productoEstrictoCumplido = reglasCumplidas.contains(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        
        
        if(deudaActivaCumplida) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.RECHAZADO, MotorConstantes.REGLA_DEUDA_ACTIVA_APLICADA);
        
        } else if (altaSolicitudCumplida && demandaLegalCumplida && historialExcelenteCumplido && !productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.ALTO, MotorConstantes.CONJUNTO_REGLAS_7_APLICADAS);

        } else if (altaSolicitudCumplida && demandaLegalCumplida) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.RECHAZADO, MotorConstantes.CONJUNTO_REGLAS_1_APLICADAS);
        
        } else if (altaSolicitudCumplida && historialExcelenteCumplido && productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.ALTO, MotorConstantes.CONJUNTO_REGLAS_8_APLICADAS);

        } else if (altaSolicitudCumplida && productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.RECHAZADO, MotorConstantes.CONJUNTO_REGLAS_2_APLICADAS);

        } else if (demandaLegalCumplida && productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.RECHAZADO, MotorConstantes.CONJUNTO_REGLAS_3_APLICADAS);

        } else if (demandaLegalCumplida) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.ALTO, MotorConstantes.REGLA_DEMANDA_LEGAL_APLICADA);

        } else if (altaSolicitudCumplida && historialExcelenteCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.MEDIO, MotorConstantes.CONJUNTO_REGLAS_4_APLICADAS);

        } else if (altaSolicitudCumplida) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.ALTO, MotorConstantes.REGLA_ALTA_SOLICITUD_APLICADA);

        } else if (empresaNuevaCumplida && productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.ALTO, MotorConstantes.CONJUNTO_REGLAS_5_APLICADAS);

        } else if (empresaNuevaCumplida) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.MEDIO, MotorConstantes.REGLA_EMPRESA_NUEVA_APLICADA);

        } else if (productoEstrictoCumplido && historialExcelenteCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.BAJO, MotorConstantes.CONJUNTO_REGLAS_6_APLICADAS);

        } else if (productoEstrictoCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.MEDIO, MotorConstantes.REGLA_PRODUCTO_ESTRICTO_APLICADA);

        } else if (historialExcelenteCumplido) {
            return aplicarResultado(resultadoFinal, NivelRiesgo.BAJO, MotorConstantes.REGLA_HISTORIAL_EXCELENTE_APLICADA);
        }
        return aplicarResultado(resultadoFinal, NivelRiesgo.BAJO, MotorConstantes.NINGUNA_REGLA_APLICADA);
    }

    @Override
    public ResultadoEvaluacion aplicarResultado(ResultadoEvaluacion resultado, NivelRiesgo nivel, String motivoFinal) {
        resultado.setNivelRiesgo(nivel);
        resultado.setMotivoFinal(motivoFinal);
        return resultado;
    }
}
