package com.Minimalist.service;

import com.Minimalist.data.DirectivaRepository;
import com.Minimalist.data.EstudianteRepository;
import com.Minimalist.data.PAEEntity;
import com.Minimalist.data.PaeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PAEServiceImpl implements PAEService {

    private final PaeRepository paeRepository;
    private final EstudianteRepository estudianteRepository;
    private final DirectivaRepository directivaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PAEEntity> findAll() {
        return StreamSupport.stream(paeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PAEEntity findOne(Long id) {
        return paeRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("PAE no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public PAEEntity save(PAEEntity pae) {
        // Validación de la existencia del estudiante
        Optional.ofNullable(pae.getEstudiante())
                .map(estudianteRepository-> estudianteRepository.getId())
                .flatMap(estudianteRepository::findById)
                .orElseThrow(() -> new IllegalArgumentException("Estudiante no encontrado"));

        // Validación de la existencia de la Directiva (Centro Académico)
        Optional.ofNullable(pae.getCentroAcademico())
                .map(directivaRepository-> directivaRepository.getId())
                .flatMap(directivaRepository::findById)
                .orElseThrow(() -> new IllegalArgumentException("Directiva no encontrada"));

        return paeRepository.save(pae);
    }

    @Override
    @Transactional
    public PAEEntity update(Long id, PAEEntity updated) {
        return paeRepository.findById(id)
                .map(existing -> {
                    // Actualización de campos
                    BeanUtils.copyProperties(updated, existing, "id", "estudiante", "centroAcademico");

                    // Validar relaciones
                    Optional.ofNullable(updated.getEstudiante())
                            .map(estudianteRepository-> estudianteRepository.getId())
                            .flatMap(estudianteRepository::findById)
                            .ifPresent(existing::setEstudiante);

                    Optional.ofNullable(updated.getCentroAcademico())
                            .map(directivaRepository-> directivaRepository.getId())
                            .flatMap(directivaRepository::findById)
                            .ifPresent(existing::setCentroAcademico);

                    return paeRepository.save(existing);
                })
                .orElseThrow(() -> new ResourceAccessException("No se encontró el PAE con ID " + id));
    }

    @Override
    public void delete(Long id) {
        paeRepository.findById(id)
                .ifPresentOrElse(
                        paeRepository::delete,
                        () -> {
                            throw new ResourceAccessException("No se encontró el PAE con ID " + id);
                        });
    }

    @Override
    public List<PAEEntity> findByEstado(String estado) {
        return paeRepository.findByEstado(estado);
    }

    @Override
    public PAEEntity findByEstudianteId(Long estudianteId) {
        PAEEntity pae = paeRepository.findByEstudianteId(estudianteId);
        if (pae == null) {
            throw new ResourceAccessException("El estudiante con ID " + estudianteId + " no está inscrito en el PAE");
        }
        return pae;
    }
}