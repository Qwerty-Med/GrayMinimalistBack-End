package com.Minimalist.service;

import com.Minimalist.data.EstudianteEntity;
import com.Minimalist.data.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class EstudianteServiceImpl implements EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EstudianteEntity> findAll() {
        return (List<EstudianteEntity>) estudianteRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EstudianteEntity findOne(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
    }

    @Override
    public EstudianteEntity save(EstudianteEntity estudiante) {
        return estudianteRepository.save(estudiante);
    }




    @Override
    public EstudianteEntity update(Long id, EstudianteEntity estudianteActualizado) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    BeanUtils.copyProperties(estudianteActualizado, estudiante, "id");
                    return estudianteRepository.save(estudiante);
                })
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + id));
    }

    @Override
    public List<EstudianteEntity> findByNombreContainingIgnoreCase(String termino) {
        return estudianteRepository.findByNombreContainingIgnoreCase(termino);
    }

    @Override
    public void deleteEstudiante(Long id) {
        estudianteRepository.findById(id)
                .ifPresentOrElse(estudianteRepository::delete,
                        () -> { throw new IllegalArgumentException("Estudiante no encontrado con ID: " + id); });
    }
}

