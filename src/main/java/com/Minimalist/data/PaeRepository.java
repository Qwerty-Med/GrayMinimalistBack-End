package com.Minimalist.data;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PaeRepository extends CrudRepository<PAEEntity, Long> {
    // 🔍 Buscar PAE por estado (Activo, En seguimiento, Finalizado, etc.)
    List<PAEEntity> findByEstado(String estado);
}
