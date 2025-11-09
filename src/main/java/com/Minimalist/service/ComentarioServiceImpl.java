package com.Minimalist.service;

import com.Minimalist.data.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
@Service
@RequiredArgsConstructor
@Transactional
public class ComentarioServiceImpl implements ComentarioService{

    private final ComentarioRepository comentarioRepository;
    private final EstudianteRepository estudianteRepository;
    private final EncuestaRepository encuestaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ComentarioEntity> findAll() {
        return StreamSupport.stream(comentarioRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public ComentarioEntity save(ComentarioEntity comentario) {

        return comentarioRepository.save(comentario);
    }

    @Override
    public ComentarioEntity update(Long id, ComentarioEntity comentarioActualizado) {
        return comentarioRepository.findById(id)
                .map(comentario -> {
                    comentario.setContenido(comentarioActualizado.getContenido());

                    return comentarioRepository.save(comentario);
                })
                .orElseThrow(() -> new ResourceAccessException("No se puede actualizar, comentario no encontrado"));
    }


    @Override
    @Transactional(readOnly = true)
    public ComentarioEntity findOne(Long id) {
        return comentarioRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Comentario no encontrado con id: " + id));
    }

    @Override
    public void deleteComentarioEntity(Long id) {
        Optional.ofNullable(id)
                .flatMap(comentarioRepository::findById)
                .ifPresentOrElse(
                        comentarioRepository::delete,
                        () -> { throw new ResourceAccessException("Comentario no encontrado con id: " + id); }
                );
    }
}



