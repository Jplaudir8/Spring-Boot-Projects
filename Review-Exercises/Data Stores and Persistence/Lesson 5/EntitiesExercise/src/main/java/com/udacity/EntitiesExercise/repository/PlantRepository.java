package com.udacity.EntitiesExercise.repository;

import com.udacity.EntitiesExercise.domain.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

    // check if a plant by this id exists where delivery has been completed
    // This kind of method is known as a "derived query method".
    Boolean existsPlantByIdAndDeliveryCompleted(Long id, Boolean delivered);

    // you can return a primitive directly
    @Query("select p.delivery.completed from Plant p where p.id = :plantId")
    Boolean deliveryCompleted(Long plantId);

    // to return a wrapper class, may need to construct it as a projection
    @Query("select new java.lang.Boolean(p.delivery.completed) from Plant p where p.id = :plantId")
    Boolean deliveryCompletedBoolean(Long plantId);

    // we can do this entirely with the method name instead of:
    //@Query("select p.name from Plant p where p.price <= :price")
    List<Plant> findByPriceLessThan(BigDecimal price);
}
