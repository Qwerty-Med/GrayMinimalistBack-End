package com.Minimalist.service;

import com.Minimalist.data.CharlaIAEntity;
import com.Minimalist.data.CharlaIARepository;
import com.Minimalist.util.ResponseRest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CharlaIAServiceImpl implements CharlaIAService{

    private final CharlaIARepository charlaIARepository;
    @Override
    public ResponseEntity<List<CharlaIAEntity>> findAll() {
        ResponseRest response = new ResponseRest();
        List<CharlaIAEntity> list = new ArrayList();
        return (ResponseEntity) Optional.ofNullable(charlaIARepository.findAll()).map(charlaIAEntities -> {
            response.setMetadata("Response ok", "00", "All charlas");
            response.
            return new ResponseEntity(response, HttpStatus.OK);
        }).orElseGet(() -> {
            response.setMetadata("Response not ok", "-1", "Don't save category");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        });
    }

    @Override
    public CharlaIAEntity save(CharlaIAEntity charla) {
        return charlaIARepository.save(charla);
    }

    @Override
    public CharlaIAEntity findOne(Long id) {
        return charlaIARepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCharlaIAEntity(Long id) {
        charlaIARepository.deleteById(id);
    }
}
