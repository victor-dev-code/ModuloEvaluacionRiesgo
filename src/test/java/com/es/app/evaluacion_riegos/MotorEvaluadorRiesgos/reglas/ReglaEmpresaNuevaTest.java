package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaEmpresaNueva;
import com.es.app.evaluacion_riegos.providers.VerificacionLegalProvider;
import com.es.app.evaluacion_riegos.providers.entity.VerificacionLegalEntity;

public class ReglaEmpresaNuevaTest {
    private VerificacionLegalProvider verificacionLegalProvider;
    private ReglaEmpresaNueva regla;
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        verificacionLegalProvider = Mockito.mock(VerificacionLegalProvider.class);
        regla = new ReglaEmpresaNueva(verificacionLegalProvider);

        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Empresa Nueva")
    void aplicarRegla() {
        VerificacionLegalEntity verificacionLegalMock = new VerificacionLegalEntity();
        verificacionLegalMock.setFechaCreacion(LocalDate.now().minusMonths(10));
        
        when(verificacionLegalProvider.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(verificacionLegalMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);
        
        assertTrue(resultado.isEstaCumplida());

    }

    @Test
    @DisplayName("No debe cumplir con la regla Empresa Nueva")
    void aplicarReglaNoCumplida() {
        VerificacionLegalEntity verificacionLegalMock = new VerificacionLegalEntity();
        verificacionLegalMock.setFechaCreacion(LocalDate.now().minusMonths(19));

        when(verificacionLegalProvider.obtenerDatosLegales(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(verificacionLegalMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);
        
        assertFalse(resultado.isEstaCumplida());

    }
}
