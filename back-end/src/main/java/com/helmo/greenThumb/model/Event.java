package com.helmo.greenThumb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;

    private String description;



    private int cycle;



    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    @ManyToOne
    @JoinColumn(name = "plant_id")
    private Plant plant;

    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;




    @OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NotificationLog> notificationLogs;

    public Event(Long id ,String title, String description, int cycle, Plant plant, User user, Date eventDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.cycle = cycle;
        this.plant = plant;
        this.user = user;
        this.eventDate = eventDate;
    }
    public Event() {
    }


    public void addEvent() {
        this.eventDate = new Date();
    }

    public Event getEvent() {
        return this;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }



    public void setUser(User author) {
        this.user = author;
    }



    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }
    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public User getUser() {
        return user;
    }
    public List<NotificationLog> getNotificationLogs() {
        return notificationLogs;
    }

    public void setNotificationLogs(List<NotificationLog> notificationLogs) {
        this.notificationLogs = notificationLogs;
    }


}
