package mukhammed.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import mukhammed.config.DatabaseConnection;
import mukhammed.entities.Programmer;
import mukhammed.entities.Project;

import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public class ProgrammerDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    public String save(Programmer newProgrammer) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newProgrammer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }
        return "Programmer with name: " + newProgrammer.getFullName() + " successfully saved";
    }

    public Optional<Programmer> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Programmer programmer = null;
        try {
            entityManager.getTransaction().begin();
            programmer = entityManager
                    .createQuery("select p from Programmer p where p.id = :parId", Programmer.class)
                    .setParameter("parId", id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return Optional.ofNullable(programmer);
    }

    public String assignProgrammerToProject(Long programmerId, Long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Programmer programmer = null;
        Project project = null;
        try {
            entityManager.getTransaction().begin();
            programmer = entityManager.find(Programmer.class, programmerId);
            project = entityManager.find(Project.class, projectId);
            programmer.addProject(project);
            project.addProgrammer(programmer);
            entityManager.persist(programmer);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        } finally {
            entityManager.close();
        }
        return "Successfully assign Project: " +programmer.getFullName();
    }

    public String assignProgrammersToProject(List<Long> programmersIds, Long projectId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Project project = entityManager.find(Project.class, projectId);
            List<Programmer> programmers = entityManager.createQuery("""
                            select p from Programmer p
                            where p.id in :parIds
                            """, Programmer.class)
                    .setParameter("parIds", programmersIds)
                    .getResultList();
            project.getProgrammers().addAll(programmers);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            return e.getMessage();
        }finally {
            entityManager.close();
        }
        return "Assign programmers to project success";
    }
}
