package com.Minimalist.precentation;

import com.Minimalist.data.ComentarioEntity;
import com.Minimalist.service.ComentarioService;
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
@RequestMapping(value = "/v1/comentarios", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ComentarioController {
    @Autowired
    private  ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    /**
     * Obtener todos los comentarios
     */
    @GetMapping
    public ResponseEntity<List<ComentarioEntity>> getAllComentarios() {
        List<ComentarioEntity> comentarios = comentarioService.findAll();

        return comentarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(comentarios);
    }

    /**
     * Obtener un comentario por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<ComentarioEntity> getComentarioById(@PathVariable Long id) {
        return Optional.ofNullable(comentarioService.findOne(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crear un nuevo comentario
     */
    @PostMapping("/api/comentarios/")
    public ResponseEntity<ComentarioEntity> createComentario(@RequestBody ComentarioEntity comentario) {
        ComentarioEntity nuevoComentario = comentarioService.save(comentario);
        return ResponseEntity.ok(nuevoComentario);
    }

    /**
     * Actualizar un comentario existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<ComentarioEntity> updateComentario(
            @PathVariable Long id,
            @RequestBody ComentarioEntity comentarioActualizado) {

        return Optional.ofNullable(comentarioService.update(id, comentarioActualizado))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Eliminar un comentario por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComentario(@PathVariable Long id) {
        try {
            comentarioService.deleteComentarioEntity(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
