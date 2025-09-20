package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaHistorialExcelente;
import com.es.app.evaluacion_riegos.providers.HistorialPagosProvider;
import com.es.app.evaluacion_riegos.providers.entity.HistorialPagosEntity;

public class ReglaHistorialExcelenteTest {
    private HistorialPagosProvider historialPagosProvider;
    private ReglaHistorialExcelente regla;
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        historialPagosProvider = Mockito.mock(HistorialPagosProvider.class);
        regla = new ReglaHistorialExcelente(historialPagosProvider);

        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Historial Excelente")
    void aplicarRegla() {
        HistorialPagosEntity historialPagosMock = new HistorialPagosEntity();
        historialPagosMock.setConPagosAtrasadosUltimos12Meses(false);
        historialPagosMock.setEnProcesoRefinanciamiento(false);

        when(historialPagosProvider.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(historialPagosMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertTrue(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Historial Excelente, cuenta con pagos atrasados")
    void aplicarReglaConPagosAtrasados() {
        HistorialPagosEntity historialPagosMock = new HistorialPagosEntity();
        historialPagosMock.setConPagosAtrasadosUltimos12Meses(true);
        historialPagosMock.setEnProcesoRefinanciamiento(false);

        when(historialPagosProvider.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(historialPagosMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Historial Excelente, cuenta con Refinanciamiento")
    void aplicarReglaConRefinanciamiento() {
        HistorialPagosEntity historialPagosMock = new HistorialPagosEntity();
        historialPagosMock.setConPagosAtrasadosUltimos12Meses(true);
        historialPagosMock.setEnProcesoRefinanciamiento(true);

        when(historialPagosProvider.obtenerHistorialPagos(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(historialPagosMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }
}
