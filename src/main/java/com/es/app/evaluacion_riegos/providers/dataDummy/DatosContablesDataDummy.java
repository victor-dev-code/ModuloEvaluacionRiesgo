package com.es.app.evaluacion_riegos.providers.dataDummy;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.providers.DatosContablesProvider;
import com.es.app.evaluacion_riegos.providers.entity.DatosContablesEntity;

@Component
public class DatosContablesDataDummy implements DatosContablesProvider {
    private final Map<String, DatosContablesEntity> dataDummyDatosContables = Map.of(
        "EMP1001", new DatosContablesEntity("EMP1001",BigDecimal.valueOf(100000.00), BigDecimal.valueOf(1200000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1023", new DatosContablesEntity("EMP1023", BigDecimal.valueOf(20000.00), BigDecimal.valueOf(240000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1005", new DatosContablesEntity("EMP1005", BigDecimal.valueOf(10000.00), BigDecimal.valueOf(120000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1205", new DatosContablesEntity("EMP1205", BigDecimal.valueOf(10000.00), BigDecimal.valueOf(120000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1307", new DatosContablesEntity("EMP1307", BigDecimal.valueOf(200000.00), BigDecimal.valueOf(2400000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1008", new DatosContablesEntity("EMP1008", BigDecimal.valueOf(200000.00), BigDecimal.valueOf(2400000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1090", new DatosContablesEntity("EMP1090", BigDecimal.valueOf(200000.00), BigDecimal.valueOf(2400000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1092", new DatosContablesEntity("EMP1092", BigDecimal.valueOf(10000.00), BigDecimal.valueOf(120000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1002", new DatosContablesEntity("EMP1002", BigDecimal.valueOf(10000.00), BigDecimal.valueOf(120000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))
        , "EMP1010", new DatosContablesEntity("EMP1010", BigDecimal.valueOf(100000.00), BigDecimal.valueOf(1200000.00), BigDecimal.valueOf(1233.0), BigDecimal.valueOf(1456.98))

    );

    @Override
    public DatosContablesEntity obtenerDatosContables(String empresaId) {
        return dataDummyDatosContables.getOrDefault(
            empresaId
            , new DatosContablesEntity(empresaId, null, null, null, null)
        );
    } 


}