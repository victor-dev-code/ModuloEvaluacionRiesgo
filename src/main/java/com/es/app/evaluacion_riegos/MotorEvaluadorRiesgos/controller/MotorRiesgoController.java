package com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.SolicitudEvaluacion;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.service.MotorRiesgosServiceImpl;
import com.es.app.evaluacion_riegos.MotorEvaluadorRiesgos.domain.dto.ResultadoEvaluacion;
@RestController
@RequestMapping("/api/v1/solicitud")
public class MotorRiesgoController {
    private final MotorRiesgosServiceImpl motorRiesgosService;

    public MotorRiesgoController(MotorRiesgosServiceImpl motorRiesgosService) {
        this.motorRiesgosService = motorRiesgosService;
    }

    @PostMapping("/evaluar")
    public ResponseEntity<ResultadoEvaluacion> obtenerResultadoEvaluacion(@RequestBody SolicitudEvaluacion solicitudEvaluacion) {
        return ResponseEntity.ok(motorRiesgosService.evaluarRiesgo(solicitudEvaluacion));
    }
    
}
