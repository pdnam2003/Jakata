package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "events")
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Event name cannot be empty")
    @Size(min = 5, message = "Event name must have at least 5 characters")
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @NotNull(message = "Date cannot be null")
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @NotEmpty(message = "Venue cannot be empty")
    @Column(name = "venue", nullable = false, length = 100)
    private String venue;

    @NotNull(message = "Seats available cannot be null")
    @Positive(message = "Seats available must be a positive number")
    @Column(name = "seats_available", nullable = false)
    private Integer seatsAvailable;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Attendee> attendees;

    public Event() {
    }

    public Event(String name, LocalDate date, String venue, Integer seatsAvailable) {
        this.name = name;
        this.date = date;
        this.venue = venue;
        this.seatsAvailable = seatsAvailable;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public Integer getSeatsAvailable() {
        return seatsAvailable;
    }

    public void setSeatsAvailable(Integer seatsAvailable) {
        this.seatsAvailable = seatsAvailable;
    }

    public Collection<Attendee> getAttendees() {
        return attendees;
    }

    public void setAttendees(Collection<Attendee> attendees) {
        this.attendees = attendees;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", venue='" + venue + '\'' +
                ", seatsAvailable=" + seatsAvailable +
                '}';
    }
}
