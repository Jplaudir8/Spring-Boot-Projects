package com.udacity.EntitiesExercise.data;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String recipentName;

    @Column(name = "address_full", length = 500) // Setting name for column in DB table and max length up to 500 characters.
    private String address;

    private LocalDateTime deliveryTime; // This includes both date and time, so it is simpler than having two separate fields.
    @Type(type = "yes_no") // This will store the values as Y or N in the DB instead of True/False.
    private Boolean isCompleted;

    // Lazy fetch is often a good idea for collection attributes. Also added
    // CascadeType.REMOVE to automatically clear any associated plants when
    // removed.
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "delivery", cascade = CascadeType.REMOVE)
    private List<Plant> plants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipentName() {
        return recipentName;
    }

    public void setRecipentName(String recipentName) {
        this.recipentName = recipentName;
    }

    public String getAddress_full() {
        return address;
    }

    public void setAddress_full(String address_full) {
        this.address = address_full;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }
}
