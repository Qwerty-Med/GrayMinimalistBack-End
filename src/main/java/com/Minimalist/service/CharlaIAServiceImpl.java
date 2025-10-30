package com.Minimalist.service;

import com.Minimalist.data.CharlaIAEntity;
import com.Minimalist.data.CharlaIARepository;
import com.Minimalist.data.EstudianteEntity;
import com.Minimalist.data.EstudianteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharlaIAServiceImpl implements CharlaIAService{

    private final CharlaIARepository charlaIARepository;
    private final EstudianteRepository estudianteRepository;


    /**
     * Obtiene todas las charlas disponibles.
     */
    @Override
    @Transactional(readOnly = true)
    public List<CharlaIAEntity> findAll() {
       return (List<CharlaIAEntity>) charlaIARepository.findAll();
    }


    /**
     * Guarda una nueva charla junto con sus asistentes (validados).
     */
    @Override
    @Transactional
    public CharlaIAEntity save(CharlaIAEntity charla) {

        return charlaIARepository.save(charla);
    }

    /**
     * Actualiza los datos de una charla existente.
     * Se actualizan tema, fecha y lista de asistentes.
     */
    @Override
    @Transactional
    public CharlaIAEntity update(Long id, CharlaIAEntity updateCharla) {
        return charlaIARepository.findById(id)
                .map(existing -> {


                    existing.setTema(updateCharla.getTema());
                    existing.setFecha(updateCharla.getFecha());
                    return charlaIARepository.save(existing);
                })
                .orElseThrow(() -> new ResourceAccessException("No existe la charla con ID: " + id));
    }

    /**
     * Busca una charla por ID.
     */
    @Override
    @Transactional(readOnly = true)
    public CharlaIAEntity findOne(Long id) {
        return charlaIARepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("No existe la charla con ID: " + id));
    }


    /**
     * Elimina una charla.
     */
    @Override
    @Transactional
    public void deleteCharlaIAEntity(Long id) {
        Optional.ofNullable(charlaIARepository.findById(id)
                        .orElseThrow(() -> new ResourceAccessException("No existe la charla con ID: " + id)))
                .ifPresent(charlaIARepository::delete);
    }
}
