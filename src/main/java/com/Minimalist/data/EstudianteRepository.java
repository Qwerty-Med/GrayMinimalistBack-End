package com.Minimalist.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EstudianteRepository extends CrudRepository<EstudianteEntity, Long> {
     List<EstudianteEntity> findByNombreContainingIgnoreCase(String termino);
}
