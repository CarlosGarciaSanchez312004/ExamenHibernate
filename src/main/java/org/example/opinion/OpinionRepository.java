package org.example.opinion;

import org.example.utils.Repository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class OpinionRepository implements Repository<Opinion> {

    SessionFactory sessionFactory;

    public OpinionRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Opinion save(Opinion entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            if (entity.getId() == null) {
                session.persist(entity);
            } else {
                entity = session.merge(entity);
            }
            session.getTransaction().commit();
            return entity;
        }
    }

    @Override
    public Optional<Opinion> delete(Opinion entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.remove(entity);
            session.getTransaction().commit();
            return Optional.ofNullable(entity);
        }
    }

    @Override
    public Optional<Opinion> deleteById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Opinion opinion = session.find(Opinion.class, id);
            if (opinion != null) {
                session.beginTransaction();
                session.remove(opinion);
                session.getTransaction().commit();
            }
            return Optional.ofNullable(opinion);
        }
    }

    @Override
    public Optional<Opinion> findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(Opinion.class, id));
        }
    }

    @Override
    public List<Opinion> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Opinion ", Opinion.class).list();
        }
    }

    @Override
    public Long count() {
        try (Session session = sessionFactory.openSession()) {
            Long salida = session.createQuery("SELECT count(g) from Opinion g", Long.class).getSingleResult();
            return salida;
        }
    }
    public List<Opinion> findByUsuario(String usuario) {
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Opinion o WHERE o.usuario = :email", Opinion.class)
                    .setParameter("email", usuario)
                    .list();
        }
    }
}