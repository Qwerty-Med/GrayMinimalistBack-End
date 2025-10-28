package com.Minimalist.precentation;

import com.Minimalist.data.CharlaIAEntity;
import com.Minimalist.service.CharlaIAService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/v1/chasrlas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CharlaIAController {

    @Autowired
    private final CharlaIAService charlaIAService;

    public CharlaIAController(CharlaIAService charlaIAService) {
        this.charlaIAService = charlaIAService;
    }

    // 🟢 Obtener todas las charlas
    @GetMapping
    public ResponseEntity<List<CharlaIAEntity>> findAll() {
        return Optional.ofNullable(charlaIAService.findAll())
                .filter(list -> !list.isEmpty())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    // 🟢 Obtener una charla por ID
    @GetMapping("/{id}")
    public ResponseEntity<CharlaIAEntity> findOne(@PathVariable Long id) {
        return Optional.ofNullable(charlaIAService.findOne(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🟡 Crear una nueva charla
    @PostMapping
    public ResponseEntity<CharlaIAEntity> create(@RequestBody CharlaIAEntity charla) {
        return Optional.ofNullable(charla)
                .map(charlaIAService::save)
                .map(saved -> ResponseEntity.status(HttpStatus.CREATED).body(saved))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    // 🟠 Actualizar una charla existente
    @PutMapping("/{id}")
    public ResponseEntity<CharlaIAEntity> update(@PathVariable Long id, @RequestBody CharlaIAEntity charla) {
        return Optional.ofNullable(charla)
                .map(c -> charlaIAService.update(id, c))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔴 Eliminar una charla
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return Optional.of(id)
                .map(i -> {
                    charlaIAService.deleteCharlaIAEntity(i);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}