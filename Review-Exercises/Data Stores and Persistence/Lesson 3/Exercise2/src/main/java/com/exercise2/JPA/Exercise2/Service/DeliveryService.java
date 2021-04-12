package com.exercise2.JPA.Exercise2.Service;

import com.exercise2.JPA.Exercise2.Delivery;
import com.exercise2.JPA.Exercise2.Repository.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {

    @Autowired
    DeliveryRepository deliveryRepository;

    public Long save(Delivery delivery) {
        delivery.getPlants().forEach(plant -> plant.setDelivery(delivery));
        deliveryRepository.persist(delivery);
        return delivery.getId();
    }

    public List<Delivery> getDeliveriesByPerson(String name) {
        return deliveryRepository.getDeliveriesByName(name);
    }

}
