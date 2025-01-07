package ciasta;

public class RecipeObject {
    private String name;
    private String ingredients;
    private String instructions;
    private int preparationTime;
    private int servings;
    // Constructor
    public RecipeObject(String name, String ingredients, String instructions, int preparationTime, int servings) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.preparationTime = preparationTime;
        this.servings = servings;
    }
    // Getter and Setter for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter and Setter for ingredients
    public String[] getIngredients() {
        return ingredients.split(",");
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = String.join(",", ingredients);
    }
    // Method to get a single ingredient by index
    public String getIngredient(int index) {
        String[] ingredientsArray = getIngredients();
        if (index >= 0 && index < ingredientsArray.length) {
            return ingredientsArray[index];
        } else {
            throw new IndexOutOfBoundsException("Invalid ingredient index");
        }
    }
    // Getter and Setter for instructions
    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    // Getter and Setter for preparationTime
    public int getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }

    // Getter and Setter for servings
    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }
}