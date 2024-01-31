package mukhammed.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import mukhammed.config.DatabaseConnection;
import mukhammed.entities.Company;
import mukhammed.entities.Project;

/**
 * @author Mukhammed Asantegin
 */
public class ProjectDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    public void save(Project newProject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newProject);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            throw new RuntimeException();
        }
    }

    public void save(Long companyId, Project newProject) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, companyId);
            company.getProjects().add(newProject);
            newProject.setCompany(company);
            entityManager.persist(newProject);
            entityManager.getTransaction().commit();
        }
        catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
