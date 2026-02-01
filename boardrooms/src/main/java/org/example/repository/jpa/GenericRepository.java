package org.example.repository.jpa;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class GenericRepository<T, ID> implements IRepository<T, ID> {

    private final Class<T> entityClass;

    public GenericRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void save(T item) {
        EntityManager em = Database.getEntityManager();
        try {
            em.getTransaction().begin();
            if (isNew(item)) {
                em.persist(item);
            } else {
                em.merge(item);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error saving: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(ID id) {
        EntityManager em = Database.getEntityManager();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error delete: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<T> findAll() {
        EntityManager em = Database.getEntityManager();
        try {
            String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, entityClass).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager em = Database.getEntityManager();
        try {
            T entity = em.find(entityClass, id);
            return Optional.ofNullable(entity);
        } finally {
            em.close();
        }
    }

    @Override
    public long count() {
        EntityManager em = Database.getEntityManager();
        try {
            String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e";
            return em.createQuery(jpql, Long.class).getSingleResult();
        } finally {
            em.close();
        }
    }

    private boolean isNew(T item) {
        try {
            Object id = item.getClass().getMethod("getId").invoke(item);
            return id == null || ((Number) id).longValue() == 0;
        } catch (Exception e) {
            return true;
        }
    }

}
