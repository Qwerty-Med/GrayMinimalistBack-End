package com.Minimalist.service;

import com.Minimalist.data.EstudianteEntity;

import java.util.List;

public interface EstudianteService {
    List<EstudianteEntity> findAll();
    EstudianteEntity findOne(Long id);
    EstudianteEntity save(EstudianteEntity estudiante);
    EstudianteEntity update(Long id, EstudianteEntity estudiante);
    void deleteEstudiante(Long id);
}
