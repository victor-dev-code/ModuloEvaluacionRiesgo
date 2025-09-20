package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaDeudaActiva;
import com.es.app.evaluacion_riegos.providers.HistorialPagosProvider;
import com.es.app.evaluacion_riegos.providers.entity.HistorialPagosEntity;

public class ReglaDeudaActivaTest {
    private HistorialPagosProvider historialPagosProvider;
    private ReglaDeudaActiva regla;
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        historialPagosProvider = Mockito.mock(HistorialPagosProvider.class);
        regla = new ReglaDeudaActiva(historialPagosProvider);

        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Deuda Activa")
    void aplicarRegla() {
        HistorialPagosEntity historialPagosMock = new HistorialPagosEntity();
        historialPagosMock.setTotalDiasAtrasados(MotorConstantes.DIAS_ATRASO);
        historialPagosMock.setTotalMesesAtrasados(MotorConstantes.MESES_ATRASADOS);

        when(historialPagosProvider.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(historialPagosMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertTrue(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Deuda Activa")
    void aplicarReglaNoCumplida() {
        HistorialPagosEntity historialPagosMock = new HistorialPagosEntity();
        historialPagosMock.setTotalDiasAtrasados(62);
        historialPagosMock.setTotalMesesAtrasados(2);

        when(historialPagosProvider.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(historialPagosMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }
}
