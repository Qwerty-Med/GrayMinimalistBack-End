package com.Minimalist.service;

import com.Minimalist.data.ComentarioEntity;
import com.Minimalist.data.DirectivaEntity;

import java.util.List;

public interface DirectivaService {
    List<DirectivaEntity> findAll();
    DirectivaEntity findOne(Long id);
    DirectivaEntity save(DirectivaEntity directiva);
    DirectivaEntity update(Long id, DirectivaEntity directiva);
    void delete(Long id);
}
