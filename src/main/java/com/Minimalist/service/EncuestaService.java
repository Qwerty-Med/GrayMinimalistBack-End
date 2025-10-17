package com.Minimalist.service;

import com.Minimalist.data.EncuestaEntity;

import java.util.List;

public interface EncuestaService {
    List<EncuestaEntity> findAll();
    EncuestaEntity findOne(Long id);
    EncuestaEntity save(EncuestaEntity encuesta);
    EncuestaEntity update(Long id, EncuestaEntity encuesta);
    void deleteEncuesta(Long id);
}
