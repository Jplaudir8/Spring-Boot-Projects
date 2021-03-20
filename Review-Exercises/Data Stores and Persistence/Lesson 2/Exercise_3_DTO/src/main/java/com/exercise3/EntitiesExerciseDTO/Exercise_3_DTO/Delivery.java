package com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO;

import com.exercise3.EntitiesExerciseDTO.Exercise_3_DTO.Inventory.Plant;
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
    private String recipientName;
    @Column(length = 500, name = "address_full")
    private String address;
    private LocalDateTime deliveryTime; // Contains both date and time
    @Type(type = "yes_no")
    private Boolean isCompleted;

    // Lazy fetch optional, but often a good idea for collection attributes
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY)
    private List<Plant> plants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public List<Plant> getPlants() {
        return plants;
    }

    public void setPlants(List<Plant> plants) {
        this.plants = plants;
    }
}
