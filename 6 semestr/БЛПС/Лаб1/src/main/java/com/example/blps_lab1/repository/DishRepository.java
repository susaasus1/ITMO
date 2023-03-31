package com.example.blps_lab1.repository;

import com.example.blps_lab1.model.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    Optional<Dish> findDishByName(String name);

    Optional<Dish> findDishById(Long id);

    Page<Dish> findAll(Pageable pageable);

    boolean existsDishByName(String name);
}
