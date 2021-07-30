package com.bytework.controller;


import com.bytework.exceptions.NotFoundException;
import com.bytework.model.Meal;
import com.bytework.model.ServiceProvider;
import com.bytework.repository.CategoryRepo;
import com.bytework.service.FoodService;
import com.bytework.utils.Constants;
import com.bytework.utils.CustomResponseCode;
import com.bytework.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Slf4j
@RestController
@RequestMapping(value = Constants.APP_CONTENT )
public class FoodController {



    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private FoodService foodService;

    @GetMapping("category")
    public List getCategories() {
        return (List) categoryRepo.findAll();
    }





    @GetMapping("restaurants/{city}")
    public List<ServiceProvider> getRestaurantByCity(@PathVariable("city") String city)
    {
        List<ServiceProvider> provider = foodService.getRestaurant(city);
        if (provider == null )
        {
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "This city does not exist");
        }
        return provider;
    }


    @GetMapping("restaurant/{id}")
    public Optional<ServiceProvider> getRestaurantByCity(@PathVariable("id") Long id)
    {
        Optional<ServiceProvider> provider = foodService.getRestaurantById(id);
        return provider;
    }




    @PostMapping("meal")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Response> createMeal(@RequestBody @Validated Meal meal)
    {
        HttpStatus httpCode;
        Response resp = new Response();
            foodService.createMenu(meal);
            resp.setCode(CustomResponseCode.SUCCESS);
            resp.setDescription("Successful");
            resp.setData(meal);
            httpCode = HttpStatus.CREATED;
        return new ResponseEntity<>(resp, httpCode);
    }


    @GetMapping("meals/{providerId}")
    public List<Meal> getMealByRestaurant(@PathVariable("providerId") Long providerId)
    {
        List<Meal> meal = foodService.getMealsByRestaurant(providerId);
        if (meal == null )
        {
            throw new NotFoundException(CustomResponseCode.NOT_FOUND_EXCEPTION, "provider does not exist");
        }
        return meal;
    }

}
