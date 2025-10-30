package com.Minimalist.data;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materias")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class MateriaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;


    private ProfesorEntity profesor;



    @ManyToOne
    @JoinColumn(name = "estudiante_id", referencedColumnName = "id")
    @JsonBackReference
    private EstudianteEntity estudiante;

    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<EvaluacionEntity> evaluaciones;

    @ManyToOne
    @JoinColumn(name = "centro_academico_id")
    @JsonBackReference
    private DirectivaEntity directiva;
}

