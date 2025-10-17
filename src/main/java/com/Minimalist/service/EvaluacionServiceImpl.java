package com.Minimalist.service;

import com.Minimalist.data.EstudianteRepository;
import com.Minimalist.data.EvaluacionEntity;
import com.Minimalist.data.EvaluacionRepository;
import com.Minimalist.data.MateriaRepository;
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
public class EvaluacionServiceImpl implements EvaluacionService {

    private final EvaluacionRepository evaluacionRepository;
    private final MateriaRepository materiaRepository;
    private final EstudianteRepository estudianteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EvaluacionEntity> findAll() {
        return StreamSupport.stream(evaluacionRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public EvaluacionEntity findOne(Long id) {
        return evaluacionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada con ID: " + id));
    }

    @Override
    public EvaluacionEntity save(EvaluacionEntity evaluacion) {
        Optional.ofNullable(evaluacion.getMateria())
                .map(m -> materiaRepository.findById(m.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Materia no encontrada con ID: " + m.getId())))
                .ifPresent(evaluacion::setMateria);

        Optional.ofNullable(evaluacion.getEstudiante())
                .map(e -> estudianteRepository.findById(e.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado con ID: " + e.getId())))
                .ifPresent(evaluacion::setEstudiante);

        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public EvaluacionEntity update(Long id, EvaluacionEntity evaluacionActualizada) {
        return evaluacionRepository.findById(id)
                .map(evaluacion -> {
                    evaluacion.setNota(evaluacionActualizada.getNota());
                    evaluacion.setTipo(evaluacionActualizada.getTipo());
                    evaluacion.setMateria(evaluacionActualizada.getMateria());
                    evaluacion.setEstudiante(evaluacionActualizada.getEstudiante());
                    return evaluacionRepository.save(evaluacion);
                })
                .orElseThrow(() -> new IllegalArgumentException("Evaluación no encontrada con ID: " + id));
    }

    @Override
    public void deleteEvaluacion(Long id) {
        evaluacionRepository.findById(id)
                .ifPresentOrElse(evaluacionRepository::delete,
                        () -> { throw new IllegalArgumentException("Evaluación no encontrada con ID: " + id); });
    }
}
