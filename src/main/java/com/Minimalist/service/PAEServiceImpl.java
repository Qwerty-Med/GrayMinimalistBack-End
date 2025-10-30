package com.Minimalist.service;

import com.Minimalist.data.DirectivaRepository;
import com.Minimalist.data.EstudianteRepository;
import com.Minimalist.data.PAEEntity;
import com.Minimalist.data.PaeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private final PaeRepository paeRepository;

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


        return paeRepository.save(pae);
    }

    @Override
    @Transactional
    public PAEEntity update(Long id, PAEEntity updated) {
        return paeRepository.findById(id)
                .map(existing -> {
                    // Actualización de campos
                    BeanUtils.copyProperties(updated, existing, "id", "estudiante", "centroAcademico");





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


}