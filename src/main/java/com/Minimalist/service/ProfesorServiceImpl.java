package com.Minimalist.service;

import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.data.ProfesorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfesorServiceImpl implements ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProfesorEntity> findAll() {
        return StreamSupport.stream(profesorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesorEntity findOne(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
    }

    @Override
    public ProfesorEntity save(ProfesorEntity profesor) {
        return profesorRepository.save(profesor);
    }

    @Override
    public ProfesorEntity update(Long id, ProfesorEntity profesorActualizado) {
        return profesorRepository.findById(id)
                .map(profesor -> {
                    profesor.setNombre(profesorActualizado.getNombre());
                    return profesorRepository.save(profesor);
                })
                .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
    }

    @Override
    public void deleteProfesor(Long id) {
        profesorRepository.findById(id)
                .ifPresentOrElse(profesorRepository::delete,
                        () -> { throw new IllegalArgumentException("Profesor no encontrado con ID: " + id); });
    }
}
