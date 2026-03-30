package com.example.dao;

import com.example.entity.Event;
import com.example.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class EventDAO {

    public void addEvent(Event event) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error adding event: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Event> getAllEvents() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Event e", Event.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Event getEventById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.find(Event.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteEvent(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Event event = em.find(Event.class, id);
            if (event != null) {
                em.remove(event);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting event: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateEvent(Event event) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(event);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating event: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
