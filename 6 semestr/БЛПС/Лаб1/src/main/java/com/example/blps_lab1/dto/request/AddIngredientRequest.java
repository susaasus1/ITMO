package com.example.blps_lab1.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddIngredientRequest {
    @NotBlank(message = "Укажите название блюда!")
    @Size(min = 1, max = 255, message = "Укажите название ингредиента! От 1 до 255 символов")
    private String ingredientName;
    @NotBlank(message = "Укажите описание рецепта!")
    @Size(min = 1, max = 255, message = "Укажите описание ингредиента! От 1 до 255 символов")
    private String description;

    public AddIngredientRequest(String ingredientName, String description) {
        this.ingredientName = ingredientName;
        this.description = description;
    }
}
