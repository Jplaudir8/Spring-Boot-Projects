package com.udacity.EntitiesExercise.controller;

import com.udacity.EntitiesExercise.data.Delivery;
import com.udacity.EntitiesExercise.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long scheduleService(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }
}
