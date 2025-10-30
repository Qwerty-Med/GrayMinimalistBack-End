package com.Minimalist.service;

import com.Minimalist.data.PAEEntity;

import java.util.List;

public interface PAEService {
    List<PAEEntity> findAll();

    PAEEntity findOne(Long id);

    PAEEntity save(PAEEntity pae);

    PAEEntity update(Long id, PAEEntity updated);

    void delete(Long id);



}