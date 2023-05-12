package com.example.blps_lab1.controller;

import com.example.blps_lab1.dto.request.AddIngredientRequest;
import com.example.blps_lab1.dto.request.UpdateIngredientRequest;
import com.example.blps_lab1.model.basic.Ingredients;
import com.example.blps_lab1.service.IngredientsService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientsService ingredientsService;

    public IngredientController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Ingredients addIngredient(@Valid @RequestBody AddIngredientRequest addIngredientRequest) {
        return ingredientsService.saveIngredient(addIngredientRequest);

    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteIngredient(@RequestParam("ingredientId") Long ingredientId) {
        ingredientsService.deleteIngredient(ingredientId);
    }

    @PutMapping()
    public Ingredients updateIngredient(@RequestParam("ingredientId") Long ingredientId,
                                        @Valid @RequestBody UpdateIngredientRequest updateIngredientRequest) {
        return ingredientsService.updateIngredient(ingredientId, updateIngredientRequest);
    }

    @GetMapping()
    public List<Ingredients> getAllIngredients(@RequestParam(value = "page", defaultValue = "1") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size) {
        return ingredientsService.getIngredientsPage(page, size).getContent();
    }

    @GetMapping("{ingredientId}")
    public Ingredients getIngredient(@PathVariable Long ingredientId) {
        return ingredientsService.getIngredient(ingredientId);
    }

}

