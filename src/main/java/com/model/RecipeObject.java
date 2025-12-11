package com.model;

import java.util.HashMap;

import com.database.Pantry;

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



    public static boolean checkPantryforRecipe(String[] ingredients) {
        HashMap<String, Integer> productsHash = new HashMap<String, Integer>();
        String helpingString;
        int count = 0;
        for(int i = 0; i < Pantry.getAllProductsMatrix2().length; i++) {    
            productsHash.put(Pantry.getAllProductsMatrix2()[i][1], Integer.valueOf(Pantry.getAllProductsMatrix2()[i][2].replaceAll("\\s.*", "")));
        }
        for(int j = 0; j < ingredients.length; j++) {
            helpingString = ingredients[j].replaceAll("\\D*$", "").substring(0, ingredients[j].lastIndexOf(" "));
            if (productsHash.containsKey(helpingString)) {
                String pantryItem = ingredients[j].substring(0, ingredients[j].lastIndexOf(" "));
                int ingredientAmount = Integer.parseInt(ingredients[j].replaceFirst("^[^\\d]*", "").replaceAll("\\D*$", ""));
                
                if (ingredientAmount <= productsHash.get(pantryItem)) {
                    count+=1;
                 }
            }
        }

        productsHash.clear();
        if (count == ingredients.length) {
            return true;
        } else {
            return false;
        }
        
    }
    private static int time;
    public static void setTimer(int time) {
        RecipeObject.time = time;
    }

    public static int getTimer() {
        return time;
    }
}
