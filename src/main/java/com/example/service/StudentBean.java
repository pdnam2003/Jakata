package com.example.service;

import com.example.entity.Student;
import javax.ejb.Stateless;
import javax.ejb.EJBException;
import javax.persistence.*;
import java.util.List;

@Stateless
public class StudentBean implements StudentBeanRemote {

    @PersistenceContext(unitName = "SportsPU")
    private EntityManager em;

    @Override
    public void addStudent(String rollnumber, String name, String email, int age) {
        try {
            Student s = new Student(rollnumber, name, email, age);
            em.persist(s);
        } catch (Exception e) {
            throw new EJBException("Error adding student", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            TypedQuery<Student> q = em.createQuery("SELECT s FROM Student s ORDER BY s.rollnumber", Student.class);
            return q.getResultList();
        } catch (Exception e) {
            throw new EJBException("Error retrieving students", e);
        }
    }

    @Override
    public Student getStudentByRoll(String rollnumber) {
        try {
            return em.find(Student.class, rollnumber);
        } catch (Exception e) {
            throw new EJBException("Error finding student with roll=" + rollnumber, e);
        }
    }

    @Override
    public void updateStudent(String rollnumber, String name, String email, int age) {
        try {
            Student s = em.find(Student.class, rollnumber);
            if (s == null) throw new EJBException("Student not found with roll=" + rollnumber);
            s.setName(name);
            s.setEmail(email);
            s.setAge(age);
            em.merge(s);
        } catch (EJBException ejb) {
            throw ejb;
        } catch (Exception e) {
            throw new EJBException("Error updating student", e);
        }
    }

    @Override
    public void deleteStudent(String rollnumber) {
        try {
            Student s = em.find(Student.class, rollnumber);
            if (s != null) em.remove(s);
        } catch (Exception e) {
            throw new EJBException("Error deleting student", e);
        }
    }
}

