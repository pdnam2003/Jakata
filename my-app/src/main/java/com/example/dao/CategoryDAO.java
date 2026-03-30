package com.example.dao;

import com.example.entity.Category;
import com.example.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import java.util.List;

public class CategoryDAO {

    public List<Category> getAllCategories() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c ORDER BY c.name", Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Category getCategoryById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }

    public void addCategory(Category category) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(category);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error adding category: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
