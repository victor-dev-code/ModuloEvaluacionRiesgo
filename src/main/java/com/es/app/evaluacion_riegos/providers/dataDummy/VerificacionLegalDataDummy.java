package com.es.app.evaluacion_riegos.providers.dataDummy;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.es.app.evaluacion_riegos.providers.VerificacionLegalProvider;
import com.es.app.evaluacion_riegos.providers.entity.VerificacionLegalEntity;

@Component
public class VerificacionLegalDataDummy implements VerificacionLegalProvider {
    private final Map<String, VerificacionLegalEntity> dataDummyVerificacionLegal = Map.of(
        "EMP1001", new VerificacionLegalEntity("EMP1001","Empresa 1001", "S.A. de C.V.", LocalDate.of(2021, 2, 21), 0)
        , "EMP1023", new VerificacionLegalEntity("EMP1023","Empresa 1023", "S.A. de C.V.", LocalDate.of(2011, 8, 21), 0)
        , "EMP1005", new VerificacionLegalEntity("EMP1005","Empresa 1005", "S.A. de C.V.", LocalDate.of(2017, 8, 21), 3)
        , "EMP1205", new VerificacionLegalEntity("EMP1205","Empresa 1205", "S.A. de C.V.", LocalDate.of(2019, 8, 21), 0)
        ,"EMP1307", new VerificacionLegalEntity("EMP1307","Empresa 1307", "S.A. de C.V.", LocalDate.of(2022, 8, 21), 0)
        ,"EMP1008", new VerificacionLegalEntity("EMP1008","Empresa 1008", "S.A. de C.V.", LocalDate.of(2009, 8, 21), 2)
        ,"EMP1090", new VerificacionLegalEntity("EMP1090","Empresa 1090", "S.A. de C.V.", LocalDate.of(2008, 8, 21), 2)
        ,"EMP1092", new VerificacionLegalEntity("EMP1092","Empresa 1092", "S.A. de C.V.", LocalDate.of(2012, 8, 21), 0)
        ,"EMP1002", new VerificacionLegalEntity("EMP1002","Empresa 1002", "S.A. de C.V.", LocalDate.of(2012, 8, 21), 0)
        ,"EMP1010", new VerificacionLegalEntity("EMP1010","Empresa 1010", "S.A. de C.V.", LocalDate.of(2024, 8, 21), 0)

    ); 

    @Override
    public VerificacionLegalEntity obtenerDatosLegales(String empresaId) {
        return dataDummyVerificacionLegal.getOrDefault(
            empresaId,
            new VerificacionLegalEntity(empresaId, null, null, null, 0)
        );
    }
    
}