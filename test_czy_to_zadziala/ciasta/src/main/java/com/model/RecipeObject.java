package com.model;

public class RecipeObject {
    private String name;
    private String ingredients;
    private String instructions;
    private int preparationTime;
    private int servings;
    private String difficulty;


    public RecipeObject(String name, String ingredients, String instructions, int preparationTime, int servings, String difficulty) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.difficulty = difficulty;
    }

   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String[] getIngredients() {
        return ingredients.split(",");
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = String.join(",", ingredients);
    }

    
    public String getIngredient(int index) {
        String[] ingredientsArray = getIngredients();
        if (index >= 0 && index < ingredientsArray.length) {
            return ingredientsArray[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid ingredient index");
        }
    }

  
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }


    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }


    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
    public String getDiff() {
        return difficulty;
    }

    public void setDiff(String difficulty) {
        this.difficulty = difficulty;
    }
}
