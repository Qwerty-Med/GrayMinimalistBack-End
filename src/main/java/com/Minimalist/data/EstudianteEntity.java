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
@Table(name = "estudiante")
public class EstudianteEntity {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    @ManyToMany
    @JoinTable(
            name = "estudiante_materia",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "materia_id")
    )
    private List<MateriaEntity> materias;

    @OneToMany(mappedBy = "estudiante")
    private List<EncuestaEntity> encuestas;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private PAEEntity pae;



    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private EvaluacionEntity evaluacion;

    @ManyToMany
    @JoinTable(
            name = "estudiante_charla",
            joinColumns = @JoinColumn(name = "estudiante_id"),
            inverseJoinColumns = @JoinColumn(name = "charla_id")
    )
    private List<CharlaIAEntity> charlas;
}
