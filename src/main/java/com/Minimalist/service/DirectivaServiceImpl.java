package com.Minimalist.service;

import com.Minimalist.data.DirectivaEntity;
import com.Minimalist.data.DirectivaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class DirectivaServiceImpl implements DirectivaService {

    private final DirectivaRepository directivaRepository;

    @Override
    public List<DirectivaEntity> findAll() {

        return StreamSupport.stream(directivaRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DirectivaEntity save(DirectivaEntity directiva) {
        return directivaRepository.save(directiva);
    }

    @Override
    @Transactional
    public DirectivaEntity update(Long id, DirectivaEntity updated) {
        return directivaRepository.findById(id)
                .map(directiva -> {
                    BeanUtils.copyProperties(updated, directiva, "id", "usuario");
                    return directivaRepository.save(directiva);
                })
                .orElseThrow(() -> new ResourceAccessException("La directiva con ID " + id + " no existe"));
    }

    @Override
    public DirectivaEntity findOne(Long id) {
        return directivaRepository.findById(id)
                .orElseThrow(() -> new ResourceAccessException("La directiva con ID " + id + " no existe"));
    }

    @Override
    public void delete(Long id) {
        directivaRepository.findById(id)
                .ifPresentOrElse(directivaRepository::delete,
                        () -> { throw new ResourceAccessException("La directiva con ID " + id + " no existe"); });
    }
}