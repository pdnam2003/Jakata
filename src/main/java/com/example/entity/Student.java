package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entity class for Student table.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(length = 50)
    private String rollnumber; // primary key

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 150)
    private String email;

    private Integer age;

    public Student() {}

    public Student(String rollnumber, String name, String email, Integer age) {
        this.rollnumber = rollnumber;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public String getRollnumber() { return rollnumber; }
    public void setRollnumber(String rollnumber) { this.rollnumber = rollnumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    @Override
    public String toString() {
        return "Student{" + "rollnumber='" + rollnumber + '\'' + ", name='" + name + '\'' + ", email='" + email + '\'' + ", age=" + age + '}';
    }
}

