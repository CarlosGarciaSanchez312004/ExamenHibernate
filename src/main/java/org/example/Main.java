package org.example;

import org.example.opinion.Opinion;
import org.example.opinion.OpinionRepository;

import org.example.pelicula.Pelicula;
import org.example.pelicula.PeliculaRepository;
import org.example.utils.DataProvider;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class Main {
    public static void main(String[] args) {

        SessionFactory factory = DataProvider.getSessionFactory();
        Pelicula nuevaPelicula = new Pelicula();
        nuevaPelicula.setTitulo("El club de la lucha");

        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(nuevaPelicula);
            session.getTransaction().commit();
            System.out.println("Película persistida: " + nuevaPelicula.getTitulo());
        }

        OpinionRepository opinionRepository = new OpinionRepository(factory);
        String correoBuscado = "user1@example.com";
        System.out.println("\n Buscando todas las opiniones para el usuario: " + correoBuscado);

        opinionRepository.findByUsuario(correoBuscado).forEach(opinion -> {
            System.out.println(opinion);
        });

        opinionRepository = new OpinionRepository(factory);

        String usuarioParaNuevaOpinion = "a@a.a";
        Opinion nuevaOpinion = new Opinion();
        nuevaOpinion.setDescripcion("La mejor pelicula que he visto en mi vida.");
        nuevaOpinion.setPuntuacion(9);
        nuevaOpinion.setUsuario(usuarioParaNuevaOpinion);
        nuevaOpinion.setPelicula(nuevaPelicula);
        opinionRepository.save(nuevaOpinion);

        PeliculaRepository peliculaRepository = new PeliculaRepository(factory);
        int puntuacionMaxima = 3;
        peliculaRepository.findPeliculasConBajaPuntuacion(puntuacionMaxima).forEach(pelicula -> {
            System.out.println("Pelicula con baja nota: " + pelicula.getTitulo());
        });
        factory.close();
    }
}