package com.example.dao;

import com.example.entity.Book;
import com.example.util.EntityManagerUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class BookDAO {

    public void addBook(Book book) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error adding book: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Book> getAllBooks() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.createQuery("SELECT b FROM Book b ORDER BY b.id DESC", Book.class).getResultList();
        } finally {
            em.close();
        }
    }

    public Book getBookById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            return em.find(Book.class, id);
        } finally {
            em.close();
        }
    }

    public void deleteBook(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Book book = em.find(Book.class, id);
            if (book != null) {
                em.remove(book);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error deleting book: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void updateBook(Book book) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating book: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
