package com.app.utils;

import com.app.entities.Exam;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;

public class ExamDAO {

    // Get all exams
    public static List<Exam> getAllExams() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.createQuery("SELECT e FROM Exam e ORDER BY e.createdAt DESC", Exam.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // Get exam by ID
    public static Exam getExamById(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Exam.class, id);
        } finally {
            em.close();
        }
    }

    // Create new exam
    public static Exam createExam(Exam exam) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            exam.setCreatedAt(LocalDateTime.now());
            exam.setUpdatedAt(LocalDateTime.now());
            em.persist(exam);
            et.commit();
            return exam;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Update exam
    public static Exam updateExam(Exam exam) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            exam.setUpdatedAt(LocalDateTime.now());
            Exam updated = em.merge(exam);
            et.commit();
            return updated;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }

    // Delete exam by ID
    public static boolean deleteExam(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            Exam exam = em.find(Exam.class, id);
            if (exam != null) {
                em.remove(exam);
                et.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (et.isActive()) {
                et.rollback();
            }
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
}
