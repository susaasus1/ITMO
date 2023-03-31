package com.example.blps_lab1.repository;

import com.example.blps_lab1.model.Dish;
import com.example.blps_lab1.model.NationalCuisine;
import com.example.blps_lab1.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByNationalCuisine(NationalCuisine nationalCuisine);

    List<Recipe> findAllByNationalCuisineAndDish(NationalCuisine nationalCuisine, Dish dish);

    List<Recipe> findAllByDish(Dish dish);

    List<Recipe> findAllById(Iterable<Long> longs);

    @Query(value = "SELECT recipe FROM recipe WHERE recipe.dish_id IN" +
            "(SELECT dish.id FROM dish WHERE UPPER(dish.name) LIKE CONCAT('%', UPPER(?2), '%'))" +
            "AND recipe.cuisine_id IN" +
            " (SELECT national_cuisine.id FROM national_cuisine WHERE UPPER(national_cuisine.cuisine) LIKE CONCAT('%', UPPER(?1), '%'))", nativeQuery = true)
    Page<Recipe> findByNationalCuisineLikeAndDishLike(String nationalCuisineFilter, String dishFilter, Pageable pageable);


}
