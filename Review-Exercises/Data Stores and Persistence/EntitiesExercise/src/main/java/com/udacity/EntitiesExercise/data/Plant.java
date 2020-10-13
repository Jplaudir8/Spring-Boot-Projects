package com.udacity.EntitiesExercise.data;

import com.fasterxml.jackson.annotation.JsonView;
import com.udacity.EntitiesExercise.Views;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

// Uses InheritanceType.JOINED to store shared
// fields in 'plant' and unique fields
// in subclass tables
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @JsonView(Views.Public.class)
    @Nationalized
    private String name;

    @JsonView(Views.Public.class)
    @Type(type = "numeric")
    @Column(precision = 12, scale = 8)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
