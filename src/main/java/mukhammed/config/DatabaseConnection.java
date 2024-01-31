package mukhammed.config;

import jakarta.persistence.EntityManagerFactory;
import mukhammed.entities.Address;
import mukhammed.entities.Company;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.util.Properties;

/**
 * @author Mukhammed Asantegin
 */
public class DatabaseConnection {
    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.JAKARTA_JDBC_DRIVER, "org.postgresql.Driver");
        properties.put(Environment.JAKARTA_JDBC_URL, "jdbc:postgresql://localhost:5434/hibernate-project");
        properties.put(Environment.JAKARTA_JDBC_USER, "postgres");
        properties.put(Environment.JAKARTA_JDBC_PASSWORD, "yiman");
        properties.put(Environment.HBM2DDL_AUTO, "update");
        properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
        properties.put(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(Address.class);
        configuration.addAnnotatedClass(Company.class);
        return configuration.buildSessionFactory();

    }
    public static EntityManagerFactory getEntityManagerFactory(){
        return getSessionFactory().unwrap(EntityManagerFactory.class);
    }
}
