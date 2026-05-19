package com.example.lanchat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Tiêu đề không được để trống")
    @Size(min = 3, max = 100, message = "Tiêu đề phải từ 3 đến 100 ký tự")
    private String title;

    @NotNull(message = "Công ty không được để trống")
    @Size(min = 2, max = 100, message = "Tên công ty phải từ 2 đến 100 ký tự")
    private String company;

    @NotNull(message = "Địa điểm không được để trống")
    @Size(min = 2, max = 100, message = "Địa điểm phải từ 2 đến 100 ký tự")
    private String location;

    @NotNull(message = "Lương không được để trống")
    @Min(value = 1000, message = "Lương phải lớn hơn hoặc bằng 1000")
    private BigDecimal salary;

    @NotNull(message = "Ngày đăng không được để trống")
    @PastOrPresent(message = "Ngày đăng phải là hôm nay hoặc trước hôm nay")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate postedDate;

    public Job() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }
}
