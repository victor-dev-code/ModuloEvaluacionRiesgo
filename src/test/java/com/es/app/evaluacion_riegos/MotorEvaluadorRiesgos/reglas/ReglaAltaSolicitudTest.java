package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaAltaSolicitud;
import com.es.app.evaluacion_riegos.providers.DatosContablesProvider;
import com.es.app.evaluacion_riegos.providers.entity.DatosContablesEntity;

public class ReglaAltaSolicitudTest {
    private DatosContablesProvider datosContablesProvider;
    private ReglaAltaSolicitud regla;
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        datosContablesProvider = Mockito.mock(DatosContablesProvider.class);
        regla = new ReglaAltaSolicitud(datosContablesProvider);


        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Alta Solicitud Vs Ventas")
    void aplicarRegla() {
        DatosContablesEntity datosContablesMock = new DatosContablesEntity();
        datosContablesMock.setIngresosMensuales(BigDecimal.valueOf(100000));

        solicitudEvaluacion.setMontoSolicitado(BigDecimal.valueOf(900000));

        when(datosContablesProvider.obtenerDatosContables(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(datosContablesMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertTrue(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Alta Solicitud Vs Ventas")
    void aplicarReglaNoCumplida() {
        DatosContablesEntity datosContablesMock = new DatosContablesEntity();
        datosContablesMock.setIngresosMensuales(BigDecimal.valueOf(100000));

        solicitudEvaluacion.setMontoSolicitado(BigDecimal.valueOf(500000));

        when(datosContablesProvider.obtenerDatosContables(solicitudEvaluacion.getEmpresaId()))
            .thenReturn(datosContablesMock);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }
}
