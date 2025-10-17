package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comentario")
public class ComentarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String contenido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "encuesta_id")
    private EncuestaEntity encuesta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estudiante_id")
    private EstudianteEntity estudiante;
}

