package com.Minimalist.precentation;

import com.Minimalist.data.MateriaEntity;
import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.service.MateriaService;
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
@RequestMapping(value= {"/v1/materias"})
@RequiredArgsConstructor
public class MateriaController {

    private final  MateriaService materiaService;


    @GetMapping
    public ResponseEntity<List<MateriaEntity>> getAll() {
        var materias = materiaService.findAll();
        return materias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(materias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaEntity> getById(@PathVariable Long id) {
        return Optional.ofNullable(materiaService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<MateriaEntity> create(@RequestBody MateriaEntity materia) {
       var  nuevo = materiaService.save(materia);
        return ResponseEntity.ok(nuevo);
    }



    @PutMapping(value = "/update/{id}")
    public ResponseEntity<MateriaEntity> update(@PathVariable Long id, @RequestBody MateriaEntity materia) {
        return Optional.ofNullable(materiaService.update(id, materia))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            materiaService.deleteMateria(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping({"/term/{name}"})
    public ResponseEntity<List<MateriaEntity>> getByTerm(@PathVariable String name) {
        return Optional.ofNullable(materiaService.findByNombreContainingIgnoreCase(name))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

