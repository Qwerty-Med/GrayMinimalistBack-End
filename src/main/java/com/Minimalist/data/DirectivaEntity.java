package com.Minimalist.data;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "directiva")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DirectivaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;



    private List<String> estudiantes;


    private List<String> materias;


    private List<String> charlas;


    private List<String> encuestas;


    private List<String> profesores;

}
