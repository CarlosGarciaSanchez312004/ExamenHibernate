package org.example.utils;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica que define las operaciones CRUD básicas
 * para repositorios de entidades.
 */
public interface Repository<T> {

    /**
     * Guarda una entidad en la base de datos.
     * Si la entidad es nueva (id = null), realiza un INSERT.
     * Si la entidad existe (id != null), realiza un UPDATE.
     */
    T save(T entity);

    /**
     * Elimina una entidad de la base de datos.
     */
    Optional<T> delete(T entity);

    /**
     * Elimina una entidad por su identificador.
     */
    Optional<T> deleteById(Long id);

    /**
     * Busca una entidad por su identificador.
     */
    Optional<T> findById(Long id);

    /**
     * Obtiene todas las entidades de este tipo.
     */
    List<T> findAll();

    /**
     * Cuenta el número total de entidades en la base de datos.
     */
    Long count();
}