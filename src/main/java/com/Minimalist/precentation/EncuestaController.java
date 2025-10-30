package com.Minimalist.precentation;


import com.Minimalist.data.EncuestaEntity;
import com.Minimalist.data.EstudianteEntity;
import com.Minimalist.service.EncuestaService;
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
@RequestMapping(value = {"/v1/encuestas"})
public class EncuestaController {

    @Autowired
    private  EncuestaService encuestaService;

    public EncuestaController(EncuestaService encuestaService) {
        this.encuestaService = encuestaService;
    }

    @GetMapping
    public ResponseEntity<List<EncuestaEntity>> getAllEncuestas() {
        List<EncuestaEntity> encuestas = encuestaService.findAll();
        return encuestas.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(encuestas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EncuestaEntity> getEncuestaById(@PathVariable Long id) {
        return Optional.ofNullable(encuestaService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EncuestaEntity> createEncuesta(@RequestBody EncuestaEntity encuesta) {
        EncuestaEntity nuevaEncuesta = encuestaService.save(encuesta);
        return ResponseEntity.ok(nuevaEncuesta);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<EncuestaEntity> updateEncuesta(
            @PathVariable Long id,
            @RequestBody EncuestaEntity encuestaActualizada) {

        return Optional.ofNullable(encuestaService.update(id, encuestaActualizada))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncuesta(@PathVariable Long id) {
        try {
            encuestaService.deleteEncuesta(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping({"/term/{name}"})
    public ResponseEntity<List<EncuestaEntity>> getByTerm(@PathVariable String name) {
        return Optional.ofNullable(encuestaService.findByNombreContainingIgnoreCase(name))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
