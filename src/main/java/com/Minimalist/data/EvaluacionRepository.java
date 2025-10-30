package com.Minimalist.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EvaluacionRepository extends CrudRepository<EvaluacionEntity, Long> {
    List<EvaluacionEntity> findByNombreContainingIgnoreCase(String termino);
}
