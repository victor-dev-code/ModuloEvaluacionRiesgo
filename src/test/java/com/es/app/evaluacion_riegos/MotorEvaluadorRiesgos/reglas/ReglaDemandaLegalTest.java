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
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaDemandaLegal;
import com.es.app.evaluacion_riegos.providers.VerificacionLegalProvider;
import com.es.app.evaluacion_riegos.providers.entity.VerificacionLegalEntity;

public class ReglaDemandaLegalTest {
    private VerificacionLegalProvider verificacionLegalProvider;
    private ReglaDemandaLegal regla = new ReglaDemandaLegal(verificacionLegalProvider);
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        verificacionLegalProvider = Mockito.mock(VerificacionLegalProvider.class);
        regla = new ReglaDemandaLegal(verificacionLegalProvider);

        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Demanda Legal Abierta")
    void aplicarRegla() {
        VerificacionLegalEntity datosLegalesMock = new VerificacionLegalEntity();
        datosLegalesMock.setNumeroDemandasActivas(3);

        when(verificacionLegalProvider.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(datosLegalesMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertTrue(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Alta Solicitud Vs Ventas")
    void aplicarReglaNoCumplida() {
        VerificacionLegalEntity datosLegalesMock = new VerificacionLegalEntity();
        datosLegalesMock.setNumeroDemandasActivas(0);

        when(verificacionLegalProvider.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(datosLegalesMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }
}
