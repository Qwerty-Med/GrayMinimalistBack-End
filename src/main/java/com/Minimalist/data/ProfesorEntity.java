package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "profesores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    @OneToMany(mappedBy = "profesor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MateriaEntity> materias;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    private DirectivaEntity directiva;
}
