package com.Minimalist.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MateriaRepository extends CrudRepository<MateriaEntity, Long> {
    List<MateriaEntity> findByNombreContainingIgnoreCase(String termino);


    Optional <MateriaEntity> findById(Long id);



}
