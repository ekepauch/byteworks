package com.bytework.repository;


import com.bytework.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MealRepo extends JpaRepository<Meal,Long> {

    List<Meal> findByProviderId(Long providerId);
}
