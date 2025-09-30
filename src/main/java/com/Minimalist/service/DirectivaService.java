package com.Minimalist.service;

import com.Minimalist.data.ComentarioEntity;
import com.Minimalist.data.DirectivaEntity;

import java.util.List;

public interface DirectivaService {
    public List<DirectivaEntity> findAll();

    public DirectivaEntity save (DirectivaEntity directiva);

    public DirectivaEntity findOne(Long id);

    public void deleteDirectivaEntity(Long id);
}
