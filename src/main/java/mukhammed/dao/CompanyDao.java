package mukhammed.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import mukhammed.config.DatabaseConnection;
import mukhammed.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public class CompanyDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    public boolean save(Company newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(newAddress);
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, id);
            if (company != null) {
                entityManager.remove(company);
            } else {
                return false;
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return false;
    }

    public boolean update(Long id, Company newCompany) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Company company = entityManager.find(Company.class, id);
            if (company != null) {
                entityManager.createQuery("""
                                update Company c
                                set c.name = :parName
                                where c.id = :parId
                                """)
                        .setParameter("parId", id)
                        .setParameter("parName", newCompany.getName())
                        .executeUpdate();
            } else {
                return false;
            }
            entityManager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }
        return false;
    }

    public Optional<Company> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Company findCompany = null;
        try {
            entityManager.getTransaction().begin();
            findCompany = entityManager
                    .createQuery("select c from Company c where c.id = :parId", Company.class)
                    .setParameter("parId", id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(findCompany);
    }


    public List<Company> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Company> companies = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            companies = entityManager
                    .createQuery("select c from Company c", Company.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return companies;
    }

    public void assignCompanyToAddress(Long addressId, Long companyId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager
                    .createQuery("update Company c set c.address.id = :parAddressId where c.id = :parCompanyId")
                    .setParameter("parAddressId", addressId)
                    .setParameter("parCompanyId", companyId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }
    }

    public Optional<Company> findCompanyByAddressId(Long addressId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Company company = null;
        try {
            entityManager.getTransaction().begin();
             company = entityManager
                    .createQuery("""
                            select c from Company c 
                            join c.address a 
                            where a.id = :parId
                                                        """, Company.class)
                    .setParameter("parId", addressId)
                    .getSingleResult();
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        finally {
            entityManager.close();
        }
        return Optional.ofNullable(company);
    }
}
