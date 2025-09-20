package com.es.app.evaluacion_riegos.providers;

import com.es.app.evaluacion_riegos.providers.entity.DatosContablesEntity;

public interface DatosContablesProvider {
    DatosContablesEntity obtenerDatosContables(String empresaId);
}
