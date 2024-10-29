package com.tm.foodsv.services;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;
import com.tm.foodsv.util.LabelingSystem;
import com.tm.foodsv.repositories.FoodRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private final FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Page<Food> getAllFoods(PageRequest pageable) {
        Page<Food> page = foodRepository.findAll(pageable);
        return page;
    }

    @Transactional
    public void addFood(Food food) {
        LabelingSystem.addLabels(food);
        if (!foodRepository.findByName(food.getName()).isEmpty()) {
            throw new IllegalStateException("Food with name " + food.getName() + " already exists");
        }else {
            foodRepository.save(food);
        }
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
        LabelingSystem.addLabels(food);
        foodRepository.save(food);
    }

    public Page<Food> getFoodByNameContainingAndCategory(String name, PageRequest pageable, List<Category> categories) {
        Page<Food> page = foodRepository.findByNameContainingAndCategoryIn(name, categories, pageable); 
        return page;
    }

    public Page<Food> getFoodByNameContaining(String name, PageRequest pageable) {
        Page<Food> page = foodRepository.findByNameContaining(name, pageable);
        return page;
    }

}
