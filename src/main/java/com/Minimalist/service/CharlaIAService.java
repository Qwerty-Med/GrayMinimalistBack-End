package com.Minimalist.service;

import com.Minimalist.data.CharlaIAEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CharlaIAService {

    public List<CharlaIAEntity> findAll();

    public CharlaIAEntity save (CharlaIAEntity charla);

    public CharlaIAEntity update (Long id, CharlaIAEntity updateCharla);

    public CharlaIAEntity findOne(Long id);

    public  void deleteCharlaIAEntity(Long id);
}
