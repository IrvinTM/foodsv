package com.tm.foodsv.services;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;
import com.tm.foodsv.entities.Warnings;
import com.tm.foodsv.repositories.FoodRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FoodService {
    private final double MAX_SODIUM_PER_100G = 320;
    private final double MAX_SODIUM_PER_BEVERAGE_NO_CALORIES = 45;

    private final FoodRepository foodRepository;
    private final Warnings warnings;

    public FoodService(FoodRepository foodRepository, Warnings warnings) {
        this.foodRepository = foodRepository;
        this.warnings = warnings;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public void addFood(Food food) {
        if(food.getCategory() == Category.BEVERAGES){
            if(food.getCalories() == 0 && food.getSodium() > MAX_SODIUM_PER_BEVERAGE_NO_CALORIES){
                food.getWarnings().concat(warnings.HIGH_SODIUM.toString());
            }
        }
        foodRepository.save(food);
    }

    public List<Food> getFoodByName(String name) {
        return foodRepository.findByName(name);
    }

    public List<Food> getFoodByCategory(Category category) {
        return foodRepository.findByCategory(category);
    }

    public List<Food> getFoodByNovaClasification(NovaClasification novaClasification) {
        return foodRepository.findByNovaClasification(novaClasification);
    }

    public void deleteFoodById(int id) {
        foodRepository.deleteById(id);
    }

    public void updateFood(Food food) {
        foodRepository.save(food);
    }

}
