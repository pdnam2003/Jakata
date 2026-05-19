package com.example.lanchat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    @NotNull(message = "Vui lòng chọn công việc")
    private Job job;

    @NotNull(message = "Tên ứng viên không được để trống")
    @Size(min = 3, max = 100, message = "Tên ứng viên phải từ 3 đến 100 ký tự")
    private String applicantName;

    @NotNull(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotNull(message = "Resume không được để trống")
    @Size(min = 20, max = 5000, message = "Resume phải ít nhất 20 ký tự")
    @Column(columnDefinition = "TEXT")
    private String resume;

    @NotNull(message = "Ngày ứng tuyển không được để trống")
    @PastOrPresent(message = "Ngày ứng tuyển phải là hôm nay hoặc trước hôm nay")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate applicationDate;

    public Application() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(LocalDate applicationDate) {
        this.applicationDate = applicationDate;
    }
}
