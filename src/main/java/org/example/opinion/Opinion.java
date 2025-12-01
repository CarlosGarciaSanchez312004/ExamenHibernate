package org.example.opinion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.pelicula.Pelicula;

@Entity
@Table(name = "opinion")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descripcion;

    private int puntuacion;

    private String usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pelicula_id")
    private Pelicula pelicula;

    @Override
    public String toString() {
        return "Opinion{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", puntuacion=" + puntuacion +
                ", usuario='" + usuario + '\'' +
                ", pelicula=" + pelicula +
                '}';
    }
}
