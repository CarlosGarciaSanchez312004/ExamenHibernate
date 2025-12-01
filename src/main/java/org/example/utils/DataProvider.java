package org.example.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Proveedor centralizado de la factoría de sesiones de Hibernate.
 * Implementa el patrón Singleton para garantizar una única instancia
 * de SessionFactory en toda la aplicación.
 */
public class DataProvider {

    /**
     * Instancia única de SessionFactory. Es estática para garantizar
     * el patrón Singleton. Se inicializa como null y se crea bajo demanda.
     */
    private static SessionFactory sessionFactory = null;

    /**
     * Constructor privado para evitar instanciación externa.
     * Esta clase solo debe proveer métodos estáticos.
     */
    private DataProvider() {}

    /**
     * Obtiene la instancia única de SessionFactory. Si no existe,
     * la crea utilizando la configuración de hibernate.cfg.xml
     * y sobreescribe las credenciales desde variables de entorno.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            var configuration = new Configuration().configure();
            configuration.setProperty("hibernate.connection.username", System.getenv("DB_USER"));
            configuration.setProperty("hibernate.connection.password", System.getenv("DB_PASSWORD"));
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }
}