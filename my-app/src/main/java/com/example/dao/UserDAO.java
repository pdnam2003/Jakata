package com.example.dao;

import com.example.entity.User;
import com.example.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UserDAO {

    public User findByUsername(String username) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public User findByUsernameAndPassword(String username, String password) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public void addUser(User user) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error adding user: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
