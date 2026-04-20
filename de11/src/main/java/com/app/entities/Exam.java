package com.app.entities;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Exam name is required")
    @NotBlank(message = "Exam name cannot be empty")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Description is required")
    @NotBlank(message = "Description cannot be empty")
    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be a positive number")
    @Column(nullable = false)
    private Integer duration; // in minutes

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Constructors
    public Exam() {
    }

    public Exam(String name, String description, Integer duration) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // Utility method to format date for display (dd-MM-yyyy hh:mm:ss)
    public String getFormattedCreatedAt() {
        return formatDateTime(this.createdAt);
    }

    public String getFormattedUpdatedAt() {
        return formatDateTime(this.updatedAt);
    }

    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) return "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}
