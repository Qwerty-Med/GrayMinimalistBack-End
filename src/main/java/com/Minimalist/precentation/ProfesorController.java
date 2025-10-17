package com.Minimalist.precentation;

import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.service.ProfesorService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;



@RequestMapping(
        value = "/api/profesores",
        produces = {
                MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
        RequestMethod.PUT })
@RestController
public class ProfesorController {

    @Autowired
    private  ProfesorService profesorService;


    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

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

    @PostMapping("/api/profesores/")
    public ResponseEntity<ProfesorEntity> create(@RequestBody ProfesorEntity profesor) {
        var nuevo = profesorService.save(profesor);
        return ResponseEntity.ok(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesorEntity> update(@PathVariable Long id, @RequestBody ProfesorEntity profesor) {
        return Optional.ofNullable(profesorService.update(id, profesor))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            profesorService.deleteProfesor(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
