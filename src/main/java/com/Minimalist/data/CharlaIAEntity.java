package com.Minimalist.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "charlaIA")
public class CharlaIAEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tema;
    private LocalDate fecha;


    private List<String> asistente;

    private String estudiante;

    @ManyToOne
    @JoinColumn(name = "directiva_id")
    private DirectivaEntity directiva;
}
