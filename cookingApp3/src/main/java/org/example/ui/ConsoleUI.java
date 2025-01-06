package org.example.ui;

import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ConsoleUI {
    private Scanner scanner;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("1. Add Ingredient to Pantry");
        System.out.println("2. Add Recipe");
        System.out.println("3. Calculate Shopping List");
        System.out.println("4. Edit Ingredient in Pantry");
        System.out.println("5. Remove Ingredient from Pantry");
        System.out.println("6. Edit Recipe");
        System.out.println("7. Remove Recipe");
        System.out.println("8. Exit");
    }

    public int getUserChoice() {
        while (true) {
            System.out.print("Enter your choice: ");
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public Ingredient getIngredientInput() {
        System.out.print("Enter ingredient name: ");
        String name = scanner.next();
        System.out.print("Enter ingredient quantity: ");
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid quantity.");
            scanner.next(); // Clear invalid input
        }
        double quantity = scanner.nextDouble();
        return new Ingredient(name, quantity);
    }

    public Recipe getRecipeInput() {
        System.out.print("Enter recipe name: ");
        String name = scanner.next();
        System.out.print("Enter number of ingredients: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number of ingredients.");
            scanner.next(); // Clear invalid input
        }
        int numIngredients = scanner.nextInt();
        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < numIngredients; i++) {
            ingredients.add(getIngredientInput());
        }
        return new Recipe(name, ingredients);
    }

    public String getIngredientName() {
        System.out.print("Enter ingredient name: ");
        return scanner.next();
    }

    public String getRecipeName() {
        System.out.print("Enter recipe name: ");
        return scanner.next();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
