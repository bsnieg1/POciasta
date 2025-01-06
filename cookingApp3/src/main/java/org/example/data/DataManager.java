package org.example.data;

import org.example.models.Ingredient;
import org.example.models.Recipe;

import java.io.*;
import java.util.List;

public class DataManager {
    public static void savePantry(List<Ingredient> pantry, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(pantry);
        }
    }

    public static List<Ingredient> loadPantry(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Ingredient>) ois.readObject();
        }
    }

    public static void saveRecipes(List<Recipe> recipes, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(recipes);
        }
    }

    public static List<Recipe> loadRecipes(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<Recipe>) ois.readObject();
        }
    }
}
