package com.tm.foodsv.controllers;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;
import com.tm.foodsv.services.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/update")
    public void updateFood(@RequestBody Food food) {
        foodService.updateFood(food);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteFood(@PathVariable int id) {
        foodService.deleteFoodById(id);
    }

    @GetMapping("/name/{name}")
    public List<Food> getFoodByName(@PathVariable String name) {
        List<Food> foods = foodService.getFoodByName(name);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Food not found");
        } else {
            return foods;
        }
    }

    @GetMapping("/category/{category}")
    public List<Food> getFoodByCategory(@PathVariable Category category) {
        List<Food> foods = foodService.getFoodByCategory(category);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No food found in this category");
        } else {
            return foods;
        }
    }
    @GetMapping("/group/{novaClasification}")
    public List<Food> getFoodByNovaClasification(@PathVariable NovaClasification novaClasification) {
        List<Food> foods = foodService.getFoodByNovaClasification(novaClasification);
        if (foods.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No food found in this nova group");
        } else {
            return foods;
        }
    }
}
