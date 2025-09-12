package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profesor")
public class ProfesorEntity {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @OneToMany(mappedBy = "profesor")
    private List<MateriaEntity> materias;
}
