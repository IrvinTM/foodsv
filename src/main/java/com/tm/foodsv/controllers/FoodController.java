package com.tm.foodsv.controllers;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;
import com.tm.foodsv.services.FoodService;
import com.tm.foodsv.util.PageDTO;

import org.springframework.data.domain.PageRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public PageDTO<Food> getFoods(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<Food> pageReq = foodService.getAllFoods(PageRequest.of(page, size));
        return new PageDTO<Food>(pageReq);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/add")
    public Food addFood(@Valid @RequestBody Food food) {
        foodService.addFood(food);
        return food;
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

    @GetMapping("/search")
    public PageDTO<Food> getFoodByNameContainingAndCategory(@RequestParam String name,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
            @RequestParam List<Category> categories) {
        if (categories.isEmpty()) {
            Page<Food> pageReq = foodService.getFoodByNameContaining(name, PageRequest.of(page, size));
            return new PageDTO<Food>(pageReq);
        }
        Page<Food> pageReq = foodService.getFoodByNameContainingAndCategory(name, PageRequest.of(page, size),
                categories);
        return new PageDTO<Food>(pageReq);
    }
}
