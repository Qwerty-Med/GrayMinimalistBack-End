package com.Minimalist.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MateriaRepository extends CrudRepository<MateriaEntity, Long> {



    Optional <MateriaEntity> findById(Long id);



}
