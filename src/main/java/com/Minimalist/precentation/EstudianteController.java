package com.Minimalist.precentation;

import com.Minimalist.data.EstudianteEntity;
import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.service.EstudianteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/v1/estudiantes"})
public class EstudianteController {

    @Autowired
    private  EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteEntity>> getAll() {
        var estudiantes = estudianteService.findAll();
        return estudiantes.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteEntity> getById(@PathVariable Long id) {
        return Optional.ofNullable(estudianteService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<EstudianteEntity> create(@RequestBody EstudianteEntity estudiante) {

        var nuevo = estudianteService.save(estudiante);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<EstudianteEntity> update(@PathVariable Long id, @RequestBody EstudianteEntity estudiante) {
        return Optional.ofNullable(estudianteService.update(id, estudiante))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            estudianteService.deleteEstudiante(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping({"/term/{name}"})
    public ResponseEntity<List<EstudianteEntity>> getByTerm(@PathVariable String name) {
        return Optional.ofNullable(estudianteService.findByNombreContainingIgnoreCase(name))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
