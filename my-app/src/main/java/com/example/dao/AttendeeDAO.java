package com.example.dao;

import com.example.entity.Attendee;
import com.example.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AttendeeDAO {

    public void addAttendee(Attendee attendee) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(attendee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error adding attendee: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Attendee> getAttendeesByEventId(Long eventId) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Attendee a WHERE a.event.id = :eventId", Attendee.class)
                    .setParameter("eventId", eventId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Attendee getAttendeeById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.find(Attendee.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteAttendee(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Attendee attendee = em.find(Attendee.class, id);
            if (attendee != null) {
                em.remove(attendee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting attendee: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateAttendee(Attendee attendee) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(attendee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating attendee: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
