package com.Minimalist.service;

import com.Minimalist.data.MateriaEntity;
import com.Minimalist.data.MateriaRepository;
import com.Minimalist.data.ProfesorRepository;
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
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final ProfesorRepository profesorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MateriaEntity> findAll() {
        return StreamSupport.stream(materiaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MateriaEntity findOne(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada con ID: " + id));
    }

    @Override
    public MateriaEntity save(MateriaEntity materia) {
        Optional.ofNullable(materia.getProfesor())
                .map(p -> profesorRepository.findById(p.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + p.getId())))
                .ifPresent(materia::setProfesor);

        return materiaRepository.save(materia);
    }

    @Override
    public MateriaEntity update(Long id, MateriaEntity materiaActualizada) {
        return materiaRepository.findById(id)
                .map(materia -> {
                    materia.setNombre(materiaActualizada.getNombre());
                    materia.setProfesor(materiaActualizada.getProfesor());
                    return materiaRepository.save(materia);
                })
                .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada con ID: " + id));
    }

    @Override
    public void deleteMateria(Long id) {
        materiaRepository.findById(id)
                .ifPresentOrElse(materiaRepository::delete,
                        () -> { throw new IllegalArgumentException("Materia no encontrada con ID: " + id); });
    }
}
