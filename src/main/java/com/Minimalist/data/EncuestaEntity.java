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
@Table(name = "encuesta")
public class EncuestaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;

    @OneToMany(mappedBy = "encuesta")
    private List<ComentarioEntity> comentarios;
}

