package com.example.service;

import com.example.entity.Student;
import java.util.List;
import javax.ejb.Remote;

@Remote
public interface StudentBeanRemote {
    void addStudent(String rollnumber, String name, String email, int age);
    List<Student> getAllStudents();
    Student getStudentByRoll(String rollnumber);
    void updateStudent(String rollnumber, String name, String email, int age);
    void deleteStudent(String rollnumber);
}

