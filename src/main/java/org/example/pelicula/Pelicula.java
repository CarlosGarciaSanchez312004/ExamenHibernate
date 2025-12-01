package org.example.pelicula;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.opinion.Opinion;

import java.util.List;

@Entity
@Table(name = "pelicula")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL)
    private List<Opinion> opiniones;


}
