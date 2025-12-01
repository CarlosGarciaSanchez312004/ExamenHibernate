package org.example.pelicula;
import org.example.opinion.Opinion;
import org.example.utils.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PeliculaRepository implements Repository<Pelicula> {
    SessionFactory sessionFactory;

    public PeliculaRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Pelicula save(Pelicula entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (entity.getId() == null) {
                session.persist(entity);
            } else {
                entity = session.merge(entity);
            }
            session.getTransaction().commit();
            return entity;
        }    }

    @Override
    public Optional<Pelicula> delete(Pelicula entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        }
    }
    @Override
    public Optional<Pelicula> deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Pelicula pelicula = session.find(Pelicula.class, id);
            if (pelicula != null) {
                session.beginTransaction();
                session.remove(pelicula);
                session.getTransaction().commit();
            }
            return Optional.ofNullable(pelicula);
        }
    }

    @Override
    public Optional<Pelicula> findById(Long id) {
        try(Session session=sessionFactory.openSession()){
            return Optional.ofNullable(session.find(Pelicula.class, id));
        }    }

    @Override
    public List<Pelicula> findAll() {
        try(Session session=sessionFactory.openSession()){
            return session.createQuery("from Pelicula ",Pelicula.class).list();
        }
    }

    @Override
    public Long count() {
        try(Session session=sessionFactory.openSession()){
            Long salida = session.createQuery("SELECT count(g) from Pelicula g",Long.class).getSingleResult();
            return salida;
        }
    }
    public List<Pelicula> findPeliculasConBajaPuntuacion(int puntuacionMaxima) {
        try (Session session = sessionFactory.openSession()) {

            String hql = "SELECT p FROM Pelicula p JOIN p.opiniones o GROUP BY p HAVING AVG(o.puntuacion) <= :notamaxima";

            return session.createQuery(hql, Pelicula.class)
                    .setParameter("notamaxima", (double) puntuacionMaxima)
                    .list();
        }
    }
}
