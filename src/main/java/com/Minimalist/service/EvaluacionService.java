package com.Minimalist.service;

import com.Minimalist.data.EvaluacionEntity;
import com.Minimalist.data.MateriaEntity;

import java.util.List;

public interface EvaluacionService {
    List<EvaluacionEntity> findAll();
    EvaluacionEntity findOne(Long id);
    EvaluacionEntity save(EvaluacionEntity evaluacion);
    EvaluacionEntity update(Long id, EvaluacionEntity evaluacion);
    void deleteEvaluacion(Long id);
    List<EvaluacionEntity> findByNombreContainingIgnoreCase(String termino);
}
