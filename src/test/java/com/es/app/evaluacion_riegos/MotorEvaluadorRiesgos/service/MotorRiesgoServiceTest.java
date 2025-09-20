package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans.MotorConstantes;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoRegla;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.enums.NivelRiesgo;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.reglas.IReglaEvaluacion;

public class MotorRiesgoServiceTest {
    private IReglaEvaluacion reglaDeudaActiva;
    private IReglaEvaluacion reglaAltaSolicitud;
    private IReglaEvaluacion reglaDemandaLegal;
    private IReglaEvaluacion reglaEmpresaNueva;
    private IReglaEvaluacion reglaHistorialExcelente;
    private IReglaEvaluacion reglaProductoEstricto;
    private MotorRiesgosServiceImpl service;
    private SolicitudEvaluacion solicitud;

    @BeforeEach
    void setUp() {
        reglaDeudaActiva = Mockito.mock(IReglaEvaluacion.class);
        reglaAltaSolicitud = Mockito.mock(IReglaEvaluacion.class);
        reglaDemandaLegal = Mockito.mock(IReglaEvaluacion.class);
        reglaEmpresaNueva = Mockito.mock(IReglaEvaluacion.class);
        reglaHistorialExcelente = Mockito.mock(IReglaEvaluacion.class);
        reglaProductoEstricto = Mockito.mock(IReglaEvaluacion.class);

        service = new MotorRiesgosServiceImpl(
            Arrays.asList(reglaDeudaActiva, reglaAltaSolicitud, reglaDemandaLegal, reglaEmpresaNueva, reglaHistorialExcelente, reglaProductoEstricto));

        solicitud = new SolicitudEvaluacion();
        solicitud.setEmpresaId("EMP123");
    }

    @Test
    @DisplayName("Debe Rechazar Empresa Con Deuda Activa")
    void integrarPrimerRegla() {

        ResultadoRegla deudaActiva = new ResultadoRegla();
        deudaActiva.setNombreRegla(MotorConstantes.REGLA_DEUDA_ACTIVA);
        deudaActiva.setEstaCumplida(true);

        when(reglaDeudaActiva.aplicarRegla(solicitud))
            .thenReturn(deudaActiva);
        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(new ResultadoRegla());

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(reglaDeudaActiva));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.RECHAZADO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_DEUDA_ACTIVA_APLICADA, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe Rechazar Empresa Con Alta Solicitud + Demanda Legal Activa")
    void integrarPrimerConjuntoReglas() {

        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        ResultadoRegla demandaLegal = new ResultadoRegla();
        demandaLegal.setNombreRegla(MotorConstantes.REGLA_DEMANDA_LEGAL);
        demandaLegal.setEstaCumplida(true);

        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);
        when(reglaDemandaLegal.aplicarRegla(solicitud))
            .thenReturn(demandaLegal);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud, reglaDemandaLegal
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.RECHAZADO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_1_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe Rechazar Empresa Con Alta Solicitud Vs Ventas + Producto Estricto")
    void integrarSegundoConjuntoReglas() {

        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);
        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud, reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.RECHAZADO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_2_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe Rechazar Empresa Con Demanda Legal Activa + Producto Estricto")
    void integrarTercerConjuntoReglas() {

        ResultadoRegla demandaLegal = new ResultadoRegla();
        demandaLegal.setNombreRegla(MotorConstantes.REGLA_DEMANDA_LEGAL);
        demandaLegal.setEstaCumplida(true);

        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaDemandaLegal.aplicarRegla(solicitud))
            .thenReturn(demandaLegal);
        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaDemandaLegal, reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.RECHAZADO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_3_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Alto por cumplir regla Demanda Legal Activa")
    void integrarSegundaRegla() {

        ResultadoRegla demandaLegal = new ResultadoRegla();
        demandaLegal.setNombreRegla(MotorConstantes.REGLA_DEMANDA_LEGAL);
        demandaLegal.setEstaCumplida(true);

        when(reglaDemandaLegal.aplicarRegla(solicitud))
            .thenReturn(demandaLegal);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaDemandaLegal
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.ALTO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_DEMANDA_LEGAL_APLICADA, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Medio por cumplir reglas Alta Solicitud + Historial Excelente")
    void integrarCuartoConjuntoReglas() {
        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        ResultadoRegla historialExcelente = new ResultadoRegla();
        historialExcelente.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        historialExcelente.setEstaCumplida(true);

        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);
        when(reglaHistorialExcelente.aplicarRegla(solicitud))
            .thenReturn(historialExcelente);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud, reglaHistorialExcelente
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.MEDIO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_4_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Alto por cumplir regla Alta Solicitud")
    void integrarTerceraRegla() {
        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.ALTO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_ALTA_SOLICITUD_APLICADA, resultado.getMotivoFinal());
    }


    @Test
    @DisplayName("Debe obtener riesgo Alto por cumplir las reglas Empresa Nueva + Producto Estricto")
    void integrarQuintoConjuntoReglas() {
        ResultadoRegla empresaNueva = new ResultadoRegla();
        empresaNueva.setNombreRegla(MotorConstantes.REGLA_EMPRESA_NUEVA);
        empresaNueva.setEstaCumplida(true);

        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaEmpresaNueva.aplicarRegla(solicitud))
            .thenReturn(empresaNueva);
        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaEmpresaNueva, reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.ALTO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_5_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Medio por cumplir la regla Empresa Nueva")
    void integrarCuartaRegla() {
        ResultadoRegla empresaNueva = new ResultadoRegla();
        empresaNueva.setNombreRegla(MotorConstantes.REGLA_EMPRESA_NUEVA);
        empresaNueva.setEstaCumplida(true);

        when(reglaEmpresaNueva.aplicarRegla(solicitud))
            .thenReturn(empresaNueva);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaEmpresaNueva
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.MEDIO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_EMPRESA_NUEVA_APLICADA, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Bajo por cumplir las reglas Producto Estricto + Historial Excelente")
    void integrarSextoConjuntoReglas() {
        ResultadoRegla historialExcelente = new ResultadoRegla();
        historialExcelente.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        historialExcelente.setEstaCumplida(true);

        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaHistorialExcelente.aplicarRegla(solicitud))
            .thenReturn(historialExcelente);
        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaHistorialExcelente, reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.BAJO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_6_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Medio por cumplir la regla Producto Estricto")
    void integrarQuintaRegla() {
        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.MEDIO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_PRODUCTO_ESTRICTO_APLICADA, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Bajo por cumplir la regla Historial Excelente")
    void integrarSextaRegla() {
        ResultadoRegla historialExcelente = new ResultadoRegla();
        historialExcelente.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        historialExcelente.setEstaCumplida(true);

        when(reglaHistorialExcelente.aplicarRegla(solicitud))
            .thenReturn(historialExcelente);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaHistorialExcelente
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.BAJO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.REGLA_HISTORIAL_EXCELENTE_APLICADA, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Alto por cumplir las reglas Alta Solicitud + Demanda Legal + Historial Excelente")
    void integrarSeptimaRegla() {
        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        ResultadoRegla demandaLegal = new ResultadoRegla();
        demandaLegal.setNombreRegla(MotorConstantes.REGLA_DEMANDA_LEGAL);
        demandaLegal.setEstaCumplida(true);

        ResultadoRegla historialExcelente = new ResultadoRegla();
        historialExcelente.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        historialExcelente.setEstaCumplida(true);

        when(reglaHistorialExcelente.aplicarRegla(solicitud))
            .thenReturn(historialExcelente);
        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);
        when(reglaDemandaLegal.aplicarRegla(solicitud))
            .thenReturn(demandaLegal);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud, reglaDemandaLegal, reglaHistorialExcelente
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.ALTO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_7_APLICADAS, resultado.getMotivoFinal());
    }

    @Test
    @DisplayName("Debe obtener riesgo Alto por cumplir las reglas Alta Solicitud + Historial Excelente + Producto Estricto")
    void integrarOctavaRegla() {
        ResultadoRegla altaSolicitud = new ResultadoRegla();
        altaSolicitud.setNombreRegla(MotorConstantes.REGLA_ALTA_SOLICITUD);
        altaSolicitud.setEstaCumplida(true);

        ResultadoRegla historialExcelente = new ResultadoRegla();
        historialExcelente.setNombreRegla(MotorConstantes.REGLA_HISTORIAL_EXCELENTE);
        historialExcelente.setEstaCumplida(true);

        ResultadoRegla productoEstricto = new ResultadoRegla();
        productoEstricto.setNombreRegla(MotorConstantes.REGLA_PRODUCTO_ESTRICTO);
        productoEstricto.setEstaCumplida(true);

        when(reglaProductoEstricto.aplicarRegla(solicitud))
            .thenReturn(productoEstricto);
        when(reglaHistorialExcelente.aplicarRegla(solicitud))
            .thenReturn(historialExcelente);
        when(reglaAltaSolicitud.aplicarRegla(solicitud))
            .thenReturn(altaSolicitud);

        MotorRiesgosServiceImpl service =
            new MotorRiesgosServiceImpl(Arrays.asList(
                reglaAltaSolicitud, reglaHistorialExcelente, reglaProductoEstricto
            ));

        ResultadoEvaluacion resultado = service.evaluarRiesgo(solicitud);

        assertEquals(NivelRiesgo.ALTO, resultado.getNivelRiesgo());
        assertEquals(MotorConstantes.CONJUNTO_REGLAS_8_APLICADAS, resultado.getMotivoFinal());
    }

    
}
