package org.example.data;

import org.example.models.Ingredient;

import java.util.List;
import java.util.ArrayList;

public class PantryManager {
    private List<Ingredient> pantry;

    public PantryManager() {
        pantry = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) {
        pantry.add(ingredient);
    }

    public List<Ingredient> getPantry() {
        return pantry;
    }
}
