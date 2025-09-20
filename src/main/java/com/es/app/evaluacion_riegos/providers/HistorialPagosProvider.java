package com.es.app.evaluacion_riegos.providers;

import com.es.app.evaluacion_riegos.providers.entity.HistorialPagosEntity;

public interface HistorialPagosProvider {
    HistorialPagosEntity obtenerHistorialPagos(String empresaId);
}
