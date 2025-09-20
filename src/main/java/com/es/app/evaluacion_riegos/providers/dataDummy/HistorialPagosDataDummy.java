package com.es.app.evaluacion_riegos.providers.dataDummy;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.providers.HistorialPagosProvider;
import com.es.app.evaluacion_riegos.providers.entity.HistorialPagosEntity;

@Component
public class HistorialPagosDataDummy implements HistorialPagosProvider {

    private final Map<String, HistorialPagosEntity> dataDummyHistorialPagos = Map.of(
        "EMP1001", new HistorialPagosEntity("EMP1001",18, 0, 0, false, false)
        , "EMP1023", new HistorialPagosEntity("EMP1023", 23, 4, 122, true, true)
        , "EMP1005", new HistorialPagosEntity("EMP1005", 18, 0, 0, true, false)
        ,"EMP1205", new HistorialPagosEntity("EMP1205", 9, 0, 0, true, false)
        ,"EMP1307", new HistorialPagosEntity("EMP1307", 22, 0, 0, true, false)
        ,"EMP1008", new HistorialPagosEntity("EMP1008", 18, 0, 0, true, true)
        ,"EMP1090", new HistorialPagosEntity("EMP1090", 18, 0, 0, false, false)
        ,"EMP1092", new HistorialPagosEntity("EMP1092", 8, 0, 0, false, true)
        ,"EMP1002", new HistorialPagosEntity("EMP1002", 8, 0, 0, false, true)
        ,"EMP1010", new HistorialPagosEntity("EMP1010", 8, 0, 0, false, false)

        
    ); 

    @Override
    public HistorialPagosEntity obtenerHistorialPagos(String empresaId) {
        return dataDummyHistorialPagos.getOrDefault(
            empresaId, 
            new HistorialPagosEntity(empresaId, 0, 0, 0, false, false)
        );
    }
    
}