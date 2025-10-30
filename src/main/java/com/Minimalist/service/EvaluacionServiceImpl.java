package com.Minimalist.service;

import com.Minimalist.data.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EvaluacionServiceImpl implements EvaluacionService {
    @Autowired
    private  EvaluacionRepository evaluacionRepository;

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


        return evaluacionRepository.save(evaluacion);
    }

    @Override
    public EvaluacionEntity update(Long id, EvaluacionEntity evaluacionActualizada) {
        return evaluacionRepository.findById(id)
                .map(evaluacion -> {
                    evaluacion.setNota(evaluacionActualizada.getNota());
                    evaluacion.setNombre(evaluacionActualizada.getNombre());
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

    @Override
    public List<EvaluacionEntity> findByNombreContainingIgnoreCase(String termino) {
        return evaluacionRepository.findByNombreContainingIgnoreCase(termino);
    }
}
