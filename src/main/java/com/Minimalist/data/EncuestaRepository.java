package com.Minimalist.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EncuestaRepository extends CrudRepository<EncuestaEntity, Long> {
    List<EncuestaEntity> findByNombreContainingIgnoreCase(String termino);
}
