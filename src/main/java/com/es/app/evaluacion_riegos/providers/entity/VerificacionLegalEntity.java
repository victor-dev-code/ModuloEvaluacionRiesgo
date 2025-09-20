package com.es.app.evaluacion_riegos.providers.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VerificacionLegalEntity {
    private String empresaId;
    private String nombreCompleto;
    private String tipoSociedad;
    private LocalDate fechaCreacion;
    private int numeroDemandasActivas;
}