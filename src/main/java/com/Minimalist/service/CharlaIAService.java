package com.Minimalist.service;

import com.Minimalist.data.CharlaIAEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CharlaIAService {

    public ResponseEntity<List<CharlaIAEntity>> findAll();

    public ResponseEntity<CharlaIAEntity> save (CharlaIAEntity charla);

    public ResponseEntity<CharlaIAEntity> findOne(Long id);

    public  ResponseEntity<CharlaIAEntity>deleteCharlaIAEntity(Long id);
}
