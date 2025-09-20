package com.es.app.evaluacion_riegos.providers;

import com.es.app.evaluacion_riegos.providers.entity.VerificacionLegalEntity;

public interface VerificacionLegalProvider {
    VerificacionLegalEntity obtenerDatosLegales(String empresaId);
}