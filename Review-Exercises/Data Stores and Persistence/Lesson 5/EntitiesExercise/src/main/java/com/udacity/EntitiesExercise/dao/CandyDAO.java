package com.udacity.EntitiesExercise.dao;

import com.udacity.EntitiesExercise.domain.CandyData;

import java.util.List;

public interface CandyDAO {
    List<CandyData> list();
    void addToDelivery(Long candyId, Long deliveryId);
    List<CandyData> findByDelivery(Long deliveryId);
}