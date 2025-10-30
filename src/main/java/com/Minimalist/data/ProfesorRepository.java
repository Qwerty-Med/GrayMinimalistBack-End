package com.Minimalist.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProfesorRepository extends CrudRepository<ProfesorEntity, Long> {
    List<ProfesorEntity> findByNombreContainingIgnoreCase(String termino);
}
