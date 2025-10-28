package com.Minimalist.precentation;

import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.service.ProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/profesores", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ProfesorController {

    private final ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<ProfesorEntity>> getAll() {
        var profesores = profesorService.findAll();
        return profesores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(profesores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfesorEntity> getById(@PathVariable Long id) {
        return Optional.ofNullable(profesorService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ProfesorEntity> create(@RequestBody ProfesorEntity profesor) {
        var nuevo = profesorService.save(profesor);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfesorEntity> update(@PathVariable Long id, @RequestBody ProfesorEntity profesor) {
        return Optional.ofNullable(profesorService.update(id, profesor))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profesorService.deleteProfesor(id);
        return ResponseEntity.noContent().build();
    }
}
