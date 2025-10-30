package com.Minimalist.service;

import com.Minimalist.data.DirectivaEntity;
import com.Minimalist.data.DirectivaRepository;
import com.Minimalist.data.ProfesorEntity;
import com.Minimalist.data.ProfesorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProfesorServiceImpl implements ProfesorService {
    @Autowired
    private  ProfesorRepository profesorRepository;

    @Autowired
    private DirectivaRepository directivaRepository;

    public ProfesorServiceImpl(ProfesorRepository profesorRepository, DirectivaRepository directivaRepository) {
        this.profesorRepository = profesorRepository;
        this.directivaRepository = directivaRepository;
    }

    @Override
    public List<ProfesorEntity> findAll() {
        return StreamSupport.stream(profesorRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProfesorEntity> findTerm(String name) {
        return profesorRepository.findByNombreContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public ProfesorEntity findOne(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
    }

    @Override
    public ProfesorEntity save(ProfesorEntity profesor) {
        return profesorRepository.save(profesor);
    }



    public ProfesorEntity update(Long id, ProfesorEntity profesorActualizado) {
        return profesorRepository.findById(id)
                .map(profesor -> {
                    BeanUtils.copyProperties(profesorActualizado, profesor, "id");
                    return profesorRepository.save(profesor);
                })
                .orElseThrow(() -> new IllegalArgumentException("Profesor no encontrado con ID: " + id));
    }


    @Override
    public void deleteProfesor(Long id) {
        profesorRepository.findById(id)
                .ifPresentOrElse(profesorRepository::delete,
                        () -> { throw new IllegalArgumentException("Profesor no encontrado con ID: " + id); });
    }
}
