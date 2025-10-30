package com.Minimalist.precentation;


import com.Minimalist.data.EvaluacionEntity;
import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.service.EvaluacionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= {"/v1/evaluaciones"})
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;


    public EvaluacionController(EvaluacionService evaluacionService) {
        this.evaluacionService = evaluacionService;
    }

    @GetMapping
    public ResponseEntity<List<EvaluacionEntity>> getAll() {
        var evaluaciones = evaluacionService.findAll();
        return evaluaciones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionEntity> getById(@PathVariable Long id) {
        return Optional.ofNullable(evaluacionService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EvaluacionEntity> create(@RequestBody EvaluacionEntity evaluacion) {
        var nueva = evaluacionService.save(evaluacion);
        return ResponseEntity.ok(nueva);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<EvaluacionEntity> update(@PathVariable Long id, @RequestBody EvaluacionEntity evaluacion) {
        return Optional.ofNullable(evaluacionService.update(id, evaluacion))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            evaluacionService.deleteEvaluacion(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping({"/term/{name}"})
    public ResponseEntity<List<EvaluacionEntity>> getByTerm(@PathVariable String name) {
        return Optional.ofNullable(evaluacionService.findByNombreContainingIgnoreCase(name))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}