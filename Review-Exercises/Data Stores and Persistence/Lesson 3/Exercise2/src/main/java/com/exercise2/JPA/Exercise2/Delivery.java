package com.exercise2.JPA.Exercise2;

import com.exercise2.JPA.Exercise2.Inventory.Plant;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NamedQuery(
        name = "Delivery.findByName",
        query = "SELECT d FROM Delivery d WHERE d.recipientName = :name"
)

@Entity
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    @Column(name = "recipient_Name")
    private String recipientName;
    @Column(length = 500, name = "address_full")
    private String address;
    private LocalDateTime deliveryTime; // Contains both date and time
    @Type(type = "yes_no")
    private Boolean isCompleted;

    // Lazy fetch optional, but often a good idea for collection attributes
    // added CascateType.REMOVE to automatically clear any associated plants when removed
    @OneToMany(mappedBy = "delivery", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
