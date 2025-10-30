package com.Minimalist.precentation;

import com.Minimalist.data.PAEEntity;
import com.Minimalist.service.PAEService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= {"/v1/pae"})
public class PAEController {

    @Autowired
    private PAEService paeService;

    public PAEController(PAEService paeService) {
        this.paeService = paeService;
    }

    @GetMapping
    public ResponseEntity<List<PAEEntity>> findAll() {
        List<PAEEntity> lista = paeService.findAll();
        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PAEEntity> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(paeService.findOne(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<PAEEntity> save(@RequestBody PAEEntity pae) {
        return ResponseEntity.ok(paeService.save(pae));
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<PAEEntity> update(@PathVariable Long id, @RequestBody PAEEntity pae) {
        return ResponseEntity.ok(paeService.update(id, pae));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        paeService.delete(id);
        return ResponseEntity.noContent().build();
    }




}
