package com.Minimalist.service;

import com.Minimalist.data.EncuestaEntity;
import com.Minimalist.data.EncuestaRepository;
import com.Minimalist.data.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class EncuestaServiceImpl implements EncuestaService {

    private final EncuestaRepository encuestaRepository;
    private final EstudianteRepository estudianteRepository;

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
        Optional.ofNullable(encuesta.getEstudiante())
                .map(estudiante -> estudianteRepository.findById(estudiante.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + estudiante.getId())))
                .ifPresent(encuesta::setEstudiante);

        // Comentarios se guardan automáticamente por cascade
        return encuestaRepository.save(encuesta);
    }

    @Override
    public EncuestaEntity update(Long id, EncuestaEntity encuestaActualizada) {
        return encuestaRepository.findById(id)
                .map(encuesta -> {
                    encuesta.setTitulo(encuestaActualizada.getTitulo());

                    Optional.ofNullable(encuestaActualizada.getEstudiante())
                            .map(e -> estudianteRepository.findById(e.getId())
                                    .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + e.getId())))
                            .ifPresent(encuesta::setEstudiante);

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
}
