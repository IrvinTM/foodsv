package com.tm.foodsv.controllers;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public List<Food> getFoods() {
        return foodService.getAllFoods();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public void addFood(@RequestBody Food food) {
        foodService.addFood(food);
    }

    @GetMapping("/name/{name}")
    public List<Food> getFoodByName(@PathVariable String name) {
        return foodService.getFoodByName(name);
    }

    @GetMapping("/category/{category}")
    public List<Food> getFoodByCategory(@PathVariable Category category) {
        return foodService.getFoodByCategory(category);
    }
}
