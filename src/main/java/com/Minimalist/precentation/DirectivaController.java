package com.Minimalist.precentation;


import com.Minimalist.data.DirectivaEntity;
import com.Minimalist.service.DirectivaService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/directivas", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor

public class DirectivaController {

    @Autowired
    private DirectivaService directivaService;

    public DirectivaController(DirectivaService directivaService) {
        this.directivaService = directivaService;
    }

    @GetMapping
    public ResponseEntity<List<DirectivaEntity>> findAll() {
        List<DirectivaEntity> directivas = directivaService.findAll();
        return directivas.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(directivas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectivaEntity> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(directivaService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<DirectivaEntity> save(@RequestBody DirectivaEntity directiva) {
        return ResponseEntity.ok(directivaService.save(directiva));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<DirectivaEntity> update(@PathVariable Long id, @RequestBody DirectivaEntity updated) {
        return ResponseEntity.ok(directivaService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        directivaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
