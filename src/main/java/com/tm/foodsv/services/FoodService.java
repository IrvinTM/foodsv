package com.tm.foodsv.services;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.repositories.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public void addFood(Food food) {
        foodRepository.save(food);
    }

    public List<Food> getFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    public List<Food> getFoodByCategory(Category category) {
        return foodRepository.findByCategory(category);
    }

    public void deleteFoodById(int id) {
        foodRepository.deleteById(id);
    }

    public void updateFood(Food food) {
        foodRepository.save(food);
    }

}
