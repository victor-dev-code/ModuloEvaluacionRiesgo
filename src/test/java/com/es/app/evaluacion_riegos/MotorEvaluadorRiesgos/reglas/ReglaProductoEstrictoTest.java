package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.ProductoFinanciero;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.impl.ReglaProductoEstricto;

public class ReglaProductoEstrictoTest {
    private ReglaProductoEstricto regla;
    private SolicitudEvaluacion solicitudEvaluacion;

    @BeforeEach
    void configuracionInicial() {
        regla = new ReglaProductoEstricto();

        solicitudEvaluacion = new SolicitudEvaluacion();
        solicitudEvaluacion.setEmpresaId("EMP19721");
    }

    @Test
    @DisplayName("Debe cumplir con la regla Producto Estricto")
    void aplicarRegla() {
        solicitudEvaluacion.setProductoFinanciero(ProductoFinanciero.ARRENDAMIENTO_FINANCIERO);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertTrue(resultado.isEstaCumplida());
    }

    @Test
    @DisplayName("NO debe cumplir con la regla Producto Estricto")
    void aplicarReglaConPagosAtrasados() {
        solicitudEvaluacion.setProductoFinanciero(ProductoFinanciero.LINEA_OPERATIVA);

        ResultadoRegla resultado = regla.aplicarRegla(solicitudEvaluacion);

        assertFalse(resultado.isEstaCumplida());
    }
}
