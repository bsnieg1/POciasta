package org.example.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.example.app.AppManager;
//import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
//import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.example.app.Database;
import org.example.models.Ingredient;
import org.example.models.Recipe;


public class PrimaryController {
    @FXML
    private VBox boxForProduct;  

    @FXML
    private TextField newProduct;  

    @FXML
    private TextField productAmount;  

    @FXML
    private Button acceptButton;  
    

    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
        }

    @FXML
    public void switchToSpizarnia() throws IOException {
        App.setRoot("spizarnia");
        System.out.println(boxForProduct);

    }
    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary"); 
    }
    @FXML
    public void initialize() {
        if (boxForProduct != null) {
            System.out.println("VBox initialized correctly." + boxForProduct);
            fastInit();
        } else {
            System.out.println("VBox is not initialized yet." + boxForProduct);
        }
    }

    private void fastInit() {
        for (int i = 0; i < Database.getAllProductsMatrix().length; i++) {
            System.out.print(Database.getAllProductsMatrix()[i][1] + " " + Database.getAllProductsMatrix()[i][2]);
            String productName = Database.getAllProductsMatrix()[i][1]; 
            String productAmount = Database.getAllProductsMatrix()[i][2]; 
            initializeFromDatabase(productName, productAmount); 
        }
    }
    private void initializeFromDatabase(String dataProductName, String dataProductAmount) {
        
        HBox productBox = new HBox(8);  
        productBox.setAlignment(Pos.CENTER_LEFT); 
        productBox.setTranslateX(8); 
        

        Label productLabel = new Label(dataProductName);
        productLabel.setFont(new Font("Arial", 24)); 
        productLabel.setPrefWidth(332);
        productLabel.setAlignment(Pos.CENTER);

        Label amountLabel = new Label(dataProductAmount + "g");
        amountLabel.setFont(new Font("Arial", 24));
        amountLabel.setPrefWidth(200);
        amountLabel.setAlignment(Pos.CENTER);
        
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 24));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(productBox); 
            Database.deleteProduct(dataProductName);
        });
    
        Button deleteProduct = new Button("X");
        deleteProduct.setFont(new Font("Arial", 24));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(productBox);
            Database.deleteProduct(dataProductName);
        });
    
        productBox.getChildren().addAll(productLabel, amountLabel, boughtProduct, deleteProduct);
    
        boxForProduct.getChildren().add(productBox);
    }

    @FXML
    private void add() {
        Database.createTable();

        HBox newHbox = new HBox(8);  
        newHbox.setAlignment(Pos.CENTER);
        newHbox.setTranslateX(4);

        Label newProductLabel = new Label(newProduct.getText());  
        newProductLabel.setFont(new Font("Arial", 24)); 
        newProductLabel.setPrefWidth(332);
        newProductLabel.setAlignment(Pos.CENTER);

        Label productAmountLabel = new Label(productAmount.getText() + "g"); 
        productAmountLabel.setFont(new Font("Arial", 24));  
        productAmountLabel.setPrefWidth(200);
        productAmountLabel.setAlignment(Pos.CENTER);
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 24));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(newHbox);  // zmienic pozniej zeby dodawac do drugiej bazy danych
            Database.deleteProduct(newProductLabel.getText());
        });
        Button deleteProduct = new Button("X");
        deleteProduct.setFont(new Font("Arial", 24));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(newHbox);
            Database.deleteProduct(newProductLabel.getText());
        });

        newHbox.getChildren().addAll(newProductLabel, productAmountLabel,boughtProduct, deleteProduct);
        boxForProduct.getChildren().add(newHbox);
        if (productAmount.getText().isEmpty()){ 
            boxForProduct.getChildren().remove(newHbox); 
        }else{
            Database.insertProduct(newProduct.getText(), Integer.parseInt(productAmount.getText()));
        }
        
 
       
        

   
        try (ResultSet rs = Database.getProducts()) {
            while (rs != null && rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Product: " + rs.getString("product_name") + ", Amount: " + rs.getString("product_amount"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }

        
        newProduct.clear();
        productAmount.clear();
    }

    //spierdolone sa adnotacje do metod ponizej

    @FXML
    private void addIngredient() {
        Ingredient ingredient = getIngredientInput();
        pantryManager.addIngredient(ingredient);
    }

    @FXML
    private void addRecipe() {
        Recipe recipe = getRecipeInput();
        recipeManager.addRecipe(recipe);
    }

    @FXML
    private void calculateShoppingList() {
        calculateAndDisplayShoppingList();
    }

    @FXML
    private void editIngredient() {
        editIngredientInPantry();
    }

    @FXML
    private void removeIngredient() {
        removeIngredientFromPantry();
    }

    @FXML
    private void editRecipe() {
        editRecipe();
    }

    @FXML
    private void removeRecipe() {
        removeRecipe();
    }

    @FXML
    private void exitApplication() {
        saveData();
        System.exit(0);
    }

    // to jest ątpliwa czesc kodu

    @FXML
private void add() {
    Database.createTable();

    HBox newHbox = new HBox(8);  
    newHbox.setAlignment(Pos.CENTER);
    newHbox.setTranslateX(4);

    Label newProductLabel = new Label(newProduct.getText());  
    newProductLabel.setFont(new Font("Arial", 24)); 
    newProductLabel.setPrefWidth(332);
    newProductLabel.setAlignment(Pos.CENTER);

    Label productAmountLabel = new Label(productAmount.getText() + "g"); 
    productAmountLabel.setFont(new Font("Arial", 24));  
    productAmountLabel.setPrefWidth(200);
    productAmountLabel.setAlignment(Pos.CENTER);
    Button boughtProduct = new Button("✓");
    boughtProduct.setFont(new Font("Arial", 24));
    boughtProduct.setAlignment(Pos.CENTER_RIGHT);
    boughtProduct.setOnAction(e -> {
        boxForProduct.getChildren().remove(newHbox);  // zmienic pozniej zeby dodawac do drugiej bazy danych
        Database.deleteProduct(newProductLabel.getText());
    });
    Button deleteProduct = new Button("X");
    deleteProduct.setFont(new Font("Arial", 24));
    deleteProduct.setAlignment(Pos.CENTER_RIGHT);
    deleteProduct.setOnAction(e -> {
        boxForProduct.getChildren().remove(newHbox);
        Database.deleteProduct(newProductLabel.getText());
    });

    newHbox.getChildren().addAll(newProductLabel, productAmountLabel,boughtProduct, deleteProduct);
    boxForProduct.getChildren().add(newHbox);
    if (productAmount.getText().isEmpty()){ 
        boxForProduct.getChildren().remove(newHbox); 
    }else{
        Database.insertProduct(newProduct.getText(), Integer.parseInt(productAmount.getText()));
    }

    newProduct.clear();
    productAmount.clear();
}
    
}


//