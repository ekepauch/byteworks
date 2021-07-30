package com.bytework.service;


import com.bytework.exceptions.NotFoundException;
import com.bytework.model.Meal;
import com.bytework.model.ServiceProvider;
import com.bytework.repository.CityRepo;
import com.bytework.repository.MealRepo;
import com.bytework.repository.ServiceProviderRepo;
import com.bytework.utils.CustomResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {


    @Autowired
    private ServiceProviderRepo serviceProviderRepo;

    @Autowired
    private CityRepo cityRepo;

    @Autowired
    private MealRepo mealRepo;




    public List<ServiceProvider> getRestaurant(String city) {
        return serviceProviderRepo.findByCity(city);
    }


    public Optional<ServiceProvider> getRestaurantById(Long id) {
        return serviceProviderRepo.findById(id);
    }



    public Meal createMenu (Meal meal){

        Optional<ServiceProvider> provider = serviceProviderRepo.findById(meal.getProviderId());
            if(provider == null){
                throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "Invalid provider Id");
            }
        return mealRepo.save(meal);
    }


    public List<Meal> getMealsByRestaurant(Long providerId) {
        return mealRepo.findByProviderId(providerId);
    }

}
