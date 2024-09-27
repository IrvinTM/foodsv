package com.tm.foodsv.repositories;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
    List<Food> findByName(String name);
    List<Food> findByCategory(Category category);
}
