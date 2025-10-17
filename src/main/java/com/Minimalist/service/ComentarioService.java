package com.Minimalist.service;

import com.Minimalist.data.CharlaIAEntity;
import com.Minimalist.data.ComentarioEntity;

import java.util.List;

public interface ComentarioService {
    public List<ComentarioEntity> findAll();

    public ComentarioEntity save (ComentarioEntity comentario);

    ComentarioEntity update(Long id, ComentarioEntity comentarioActualizado);

    public ComentarioEntity findOne(Long id);

    public void deleteComentarioEntity(Long id);
}
