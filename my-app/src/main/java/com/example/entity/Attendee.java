package com.example.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "attendees")
public class Attendee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email must be in a valid email format")
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @NotNull(message = "Event cannot be null")
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Attendee() {
    }

    public Attendee(String name, String email, Event event) {
        this.name = name;
        this.email = email;
        this.event = event;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Attendee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", event=" + event.getId() +
                '}';
    }
}
