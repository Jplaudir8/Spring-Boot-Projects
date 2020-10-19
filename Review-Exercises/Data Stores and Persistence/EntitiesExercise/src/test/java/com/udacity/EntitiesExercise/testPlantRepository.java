package com.udacity.EntitiesExercise;

import com.udacity.EntitiesExercise.domain.Delivery;
import com.udacity.EntitiesExercise.domain.Plant;
import com.udacity.EntitiesExercise.repository.PlantRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
public class testPlantRepository {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PlantRepository plantRepository;

    @Test
    public void testPriceLessThan() {
        Plant p = testEntityManager.persist(new Plant("Eucaliptus", BigDecimal.valueOf(4.99)));
        testEntityManager.persist(new Plant ("Sunflower", BigDecimal.valueOf(5.99)));

        List<Plant> cheapPlant = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));

        Assertions.assertEquals(1, cheapPlant.size(), "Size");
        Assertions.assertEquals(p.getId(), cheapPlant.get(0).getId(), "Id");

        System.out.println(p.getName());
        System.out.println(cheapPlant.get(0).getName());
    }

    @Test
    public void testDeliveryCompleted() {
        Plant p = testEntityManager.persist(new Plant("Rose", BigDecimal.valueOf(9.99)));
        Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now()));

        d.setPlants(Lists.newArrayList(p));
        p.setDelivery(d);

        //test both before and after
        Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
        d.setCompleted(true);
        Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
    }
}
