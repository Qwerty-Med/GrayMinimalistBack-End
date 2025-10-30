package com.Minimalist.service;

import com.Minimalist.data.EncuestaEntity;
import com.Minimalist.data.EncuestaRepository;
import com.Minimalist.data.EstudianteEntity;
import com.Minimalist.data.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EncuestaServiceImpl implements EncuestaService {
    @Autowired
    private  EncuestaRepository encuestaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EncuestaEntity> findAll() {
        return StreamSupport.stream(encuestaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EncuestaEntity findOne(Long id) {
        return encuestaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada con ID: " + id));
    }

    @Override
    public EncuestaEntity save(EncuestaEntity encuesta) {


        // Comentarios se guardan automáticamente por cascade
        return encuestaRepository.save(encuesta);
    }

    @Override
    public EncuestaEntity update(Long id, EncuestaEntity encuestaActualizada) {
        return encuestaRepository.findById(id)
                .map(encuesta -> {

                    BeanUtils.copyProperties(encuestaActualizada, encuesta, "id");
                    return encuestaRepository.save(encuesta);
                })
                .orElseThrow(() -> new IllegalArgumentException("Encuesta no encontrada con ID: " + id));
    }

    @Override
    public void deleteEncuesta(Long id) {
        encuestaRepository.findById(id)
                .ifPresentOrElse(
                        encuestaRepository::delete,
                        () -> { throw new IllegalArgumentException("Encuesta no encontrada con ID: " + id); }
                );
    }

    @Override
    public List<EncuestaEntity> findByNombreContainingIgnoreCase(String termino) {
        return encuestaRepository.findByNombreContainingIgnoreCase(termino);
    }
}
