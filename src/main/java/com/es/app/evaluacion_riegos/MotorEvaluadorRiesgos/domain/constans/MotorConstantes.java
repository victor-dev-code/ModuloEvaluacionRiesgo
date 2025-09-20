package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.constans;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MotorConstantes {
    public final static int AUMENTAR_UN_PUNTO = 1;
    public final static int DISMINUIR_UN_PUNTO = 1;
    public final static int EMPRESA_RECHAZADA = 4;
    public final static int EMPRESA_PUNTAJE_ALTO = 3;
    public final static int EMPRESA_PUNTAJE_MEDIO = 2;
    public final static int EMPRESA_PUNTAJE_BAJO = 1;
    public final static int MESES_DE_EXISTENCIA = 18;
    public final static int MONTO_ALTA_SOLICITUD = 8;
    public final static int DEMANDA_ACTIVA = 1;
    public final static int DIAS_ATRASO = 91;
    public final static int MESES_ATRASADOS = 3;
    public final static int PUNTAJE_INICIAL = 1;
    public final static int PUNTAJE_MAXIMO = 4;

    public final static String REGLA_ALTA_SOLICITUD = "Alta Solicitud vs Ventas";
    public final static String REGLA_DEMANDA_LEGAL = "Demanda Legal Abierta";
    public final static String REGLA_DEUDA_ACTIVA = "Deuda Activa";
    public final static String REGLA_EMPRESA_NUEVA = "Empresa nueva";
    public final static String REGLA_HISTORIAL_EXCELENTE = "Historial Excelente";
    public final static String REGLA_PRODUCTO_ESTRICTO = "Producto Estricto";

    public final static String REGLA_DEUDA_ACTIVA_APLICADA = "Regla Determinante: Deuda Activa";
    public final static String CONJUNTO_REGLAS_1_APLICADAS = "Reglas Determinantes: Alta Solicitud + Demanda Legal Activa";
    public final static String CONJUNTO_REGLAS_2_APLICADAS = "Reglas Determinantes: Alta Solicitud Vs Ventas + Producto Estricto";
    public final static String CONJUNTO_REGLAS_3_APLICADAS = "Reglas Determinantes: Demanda Legal Activa + Producto Estricto";
    public final static String REGLA_DEMANDA_LEGAL_APLICADA = "Regla Determinante: Demanda Legal Activa";
    public final static String CONJUNTO_REGLAS_4_APLICADAS = "Reglas Determinantes: Alta Solicitud + Historial Excelente";
    public final static String REGLA_ALTA_SOLICITUD_APLICADA = "Regla Determinante: Alta Solicitud Vs Ventas";
    public final static String CONJUNTO_REGLAS_5_APLICADAS = "Reglas Determinantes: Empresa Nueva + Producto Estricto";
    public final static String REGLA_EMPRESA_NUEVA_APLICADA = "Regla Determinante: Empresa Nueva";
    public final static String CONJUNTO_REGLAS_6_APLICADAS = "Reglas Determinantes: Producto Estricto + Historial Excelente";
    public final static String REGLA_PRODUCTO_ESTRICTO_APLICADA = "Regla Determinante: Producto Estricto";
    public final static String REGLA_HISTORIAL_EXCELENTE_APLICADA = "Regla Determinante: Historial Excelente";
    public final static String NINGUNA_REGLA_APLICADA = "No aplica ninguna de las reglas";
    public final static String CONJUNTO_REGLAS_7_APLICADAS = "Reglas Determinantes: Alta Solicitud + Demanda Legal Activa + Historial Excelente";
    public final static String CONJUNTO_REGLAS_8_APLICADAS = "Reglas Determinantes: Alta Solicitud + Historial Excelente + Producto Estricto";

}
