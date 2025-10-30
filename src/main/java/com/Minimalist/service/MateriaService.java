package com.Minimalist.service;

import com.Minimalist.data.MateriaEntity;

import java.util.List;

public interface MateriaService {
    List<MateriaEntity> findAll();
    MateriaEntity findOne(Long id);
    MateriaEntity save(MateriaEntity materia);
    MateriaEntity update(Long id, MateriaEntity materia);
    void deleteMateria(Long id);

    List<MateriaEntity> findByNombreContainingIgnoreCase(String termino);
}
