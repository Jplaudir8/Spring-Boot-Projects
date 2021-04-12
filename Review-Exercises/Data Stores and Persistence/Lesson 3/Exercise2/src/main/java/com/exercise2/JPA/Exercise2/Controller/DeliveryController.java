package com.exercise2.JPA.Exercise2.Controller;

import com.exercise2.JPA.Exercise2.Delivery;
import com.exercise2.JPA.Exercise2.RecipientAndPrice;
import com.exercise2.JPA.Exercise2.Service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    DeliveryService deliveryService;

    @PostMapping
    public Long setDelivery(@RequestBody Delivery delivery) {
        return deliveryService.save(delivery);
    }

    @GetMapping("/deliveriesByPerson/{name}")
    public List<Delivery> getDeliveriesFromPerson(@PathVariable String name) {
        return deliveryService.getDeliveriesByPerson(name);
    }

    @GetMapping("/bill/{deliveryId}")
    public RecipientAndPrice getBill(@PathVariable Long deliveryId) {
        return deliveryService.getBill(deliveryId);
    }
}
