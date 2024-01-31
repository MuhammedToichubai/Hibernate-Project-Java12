package mukhammed.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import mukhammed.config.DatabaseConnection;
import mukhammed.entities.Address;
import mukhammed.entities.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Mukhammed Asantegin
 */
public class AddressDao {
    private final EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();

    public boolean save(Address newAddress) {
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

    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);

            Company company;
            try {
                company = entityManager
                        .createQuery("""
                                select c from Company c 
                                where c.address.id = :parId
                                                            """, Company.class)
                        .setParameter("parId", id)
                        .getSingleResult();
            } catch (NoResultException e) {
                company = null;
            }

            if (company != null) company.setAddress(null);

            if (address != null) {
                entityManager.createQuery("delete from Address where id = :parId")
                        .setParameter("parId", id)
                        .executeUpdate();
            }
            entityManager.getTransaction().commit();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        } finally {
            entityManager.close();
        }

    }

    public boolean update(Long id, Address newAddress) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Address address = entityManager.find(Address.class, id);
            if (address != null) {
                entityManager.createQuery("""
                                update Address a
                                set a.country = :parCountry
                                where a.id = :parId
                                """)
                        .setParameter("parId", id)
                        .setParameter("parCountry", newAddress.getCountry())
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

    public Optional<Address> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Address findAddress = null;
        try {
            entityManager.getTransaction().begin();
            findAddress = entityManager
                    .createQuery("select a from Address a where a.id = :parId", Address.class)
                    .setParameter("parId", id)
                    .getSingleResult();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return Optional.ofNullable(findAddress);
    }


    public List<Address> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Address> addresses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            addresses = entityManager
                    .createQuery("select a from Address a", Address.class)
                    .getResultList();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            System.err.println(e.getMessage());
        }
        return addresses;
    }
}
