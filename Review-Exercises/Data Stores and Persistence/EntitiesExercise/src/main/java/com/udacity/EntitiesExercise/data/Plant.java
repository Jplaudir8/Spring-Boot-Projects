package com.udacity.EntitiesExercise.data;

import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Plant {
    @Id
    @GeneratedValue
    private Long id;

    @Nationalized
    private String name;

    @Type(type = "numeric")
    @Column(precision = 12, scale = 8)
    private BigDecimal price;
}
