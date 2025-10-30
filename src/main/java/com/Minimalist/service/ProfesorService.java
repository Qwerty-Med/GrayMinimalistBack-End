package com.Minimalist.service;

import com.Minimalist.data.ProfesorEntity;

import java.util.List;

public interface ProfesorService {
    List<ProfesorEntity> findAll();
    List<ProfesorEntity> findTerm(String name);
    ProfesorEntity findOne(Long id);
    ProfesorEntity save(ProfesorEntity profesor);
    ProfesorEntity update(Long id, ProfesorEntity profesor);
    void deleteProfesor(Long id);
}
