package com.tm.foodsv.repositories;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;

//checar imports si son correctos
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {
	List<Food> findByName(String name);

	List<Food> findByCategory(Category category);

	List<Food> findByNovaClasification(NovaClasification novaClasification);

	Page<Food> findByNameContaining(String name, Pageable pageable);

	Page<Food> findByNameContainingAndCategoryIn(String name, List<Category> categories, Pageable pageable);
}
