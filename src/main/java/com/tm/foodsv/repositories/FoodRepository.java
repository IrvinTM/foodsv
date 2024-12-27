package com.tm.foodsv.repositories;

import com.tm.foodsv.entities.Category;
import com.tm.foodsv.entities.Food;
import com.tm.foodsv.entities.NovaClasification;

//checar imports si son correctos
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

	List<Food> findByName(String name);

	List<Food> findByCategory(Category category);

	List<Food> findByNovaClasification(NovaClasification novaClasification);

	Page<Food> findByNameContainingIgnoreCase(String name, Pageable pageable);

	@Query("SELECT f FROM Food f ORDER BY function('RANDOM')")
	Page<Food> findAllRamdom(Pageable pageable);

	@Query("SELECT f FROM Food f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :name, '%')) AND f.category IN :categories")
	Page<Food> findByNameContainingAndCategoryInIgnoreCase(@Param("name") String name,
			@Param("categories") List<Category> categories, Pageable pageable);

}
