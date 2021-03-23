package com.exercise2.JPA.Exercise2.Controller;

import com.exercise2.JPA.Exercise2.Delivery;
import com.exercise2.JPA.Exercise2.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long setDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

}
