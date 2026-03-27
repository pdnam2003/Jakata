package com.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDAO {

    // 1. Đăng nhập
    public User login(String email, String password) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            TypedQuery<User> query = em.createQuery(jpql, User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Không tìm thấy (Sai email hoặc mật khẩu)
        } finally {
            em.close();
        }
    }

    // 2. Lấy User theo ID
    public User getUserById(int id) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        try {
            // JPA tự động sinh câu lệnh SELECT * FROM users WHERE id = ?
            return em.find(User.class, id); 
        } finally {
            em.close();
        }
    }

    // 3. Đăng ký User
    public void registerUser(String email, String password, String name) {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            
            User user = new User();
            user.setEmail(email);
            user.setPassword(password);
            user.setName(name);
            
            em.persist(user); // Tương đương INSERT INTO...
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Hoàn tác nếu có lỗi
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}