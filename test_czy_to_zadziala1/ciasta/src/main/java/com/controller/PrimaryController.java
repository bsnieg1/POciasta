package com.controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.App;
import com.database.Database;
import com.database.Pantry;
import com.model.RecipeObject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.Optional;

public class PrimaryController {


    private RecipeObject pickedRecipe;
    private RecipeObject[] recipes = new RecipeObject[6];


    public PrimaryController() {
        recipes[0] = new RecipeObject("Ciasto marchewkowe", "marchew 100g,mąka 100g,cukier 50g,jajka 100g,olej 10g,proszek do pieczenia 5g,cynamon 5g,sól 5g", " 1. Wymieszaj suche składniki: mąkę, cukier, proszek do pieczenia, cynamon i sól.\n 2. Dodaj mokre składniki: jajka i olej.\n 3. Dodaj starte marchewki i dokładnie wymieszaj.\n 4. Przełóż ciasto do formy.\n 5. Wstaw do piekarnika na 180 stopni na 45 minut.\n 6. Po upieczeniu, ostudź przed podaniem.", 60, 12, "łatwy");
        recipes[1] = new RecipeObject("Sernik", "ser 500g,cukier 200g,jajka 100g,masło 100g,mąka 50g,proszek do pieczenia 5g,wanilia 5g,sól 5g", " 1. Wymieszaj ser z cukrem i wanilią.\n 2. Dodaj jajka i roztopione masło, dokładnie wymieszaj.\n 3. Dodaj mąkę, proszek do pieczenia i sól, wymieszaj do uzyskania jednolitej masy.\n 4. Przełóż masę serową do formy.\n 5. Wstaw do piekarnika na 180 stopni na 60 minut.\n 6. Po upieczeniu, ostudź przed podaniem.", 90, 8, "średni");
        recipes[2] = new RecipeObject("Szarlotka", "jabłka 500g,mąka 300g,cukier 200g,masło 200g,jajka 100g,proszek do pieczenia 5g,cynamon 5g,sól 5g", " 1. Wymieszaj mąkę z proszkiem do pieczenia, cynamonem i solą.\n 2. Dodaj masło i jajka, wymieszaj do uzyskania jednolitej masy.\n 3. Dodaj pokrojone jabłka i dokładnie wymieszaj.\n 4. Przełóż ciasto do formy.\n 5. Wstaw do piekarnika na 180 stopni na 50 minut.\n 6. Po upieczeniu, ostudź przed podaniem.", 70, 10, "średni");
        recipes[3] = new RecipeObject("Makowiec", "mak 200g,mąka 300g,cukier 150g,jajka 100g,masło 100g,proszek do pieczenia 5g,mleko 100g,sól 5g", " 1. Wymieszaj mąkę z proszkiem do pieczenia i solą.\n 2. Dodaj roztopione masło, jajka i mleko, dokładnie wymieszaj.\n 3. Dodaj mak i cukier, wymieszaj do uzyskania jednolitej masy.\n 4. Przełóż ciasto do formy.\n 5. Wstaw do piekarnika na 180 stopni na 60 minut.\n 6. Po upieczeniu, ostudź przed podaniem.", 90, 10, "łatwy");
        recipes[4] = new RecipeObject("Brownie", "czekolada 200g,masło 100g,cukier 150g,jajka 100g,mąka 50g,proszek do pieczenia 5g,sól 5g,orzechy 50g", " 1. Roztop czekoladę z masłem.\n 2. Dodaj cukier i jajka, dokładnie wymieszaj.\n 3. Dodaj mąkę, proszek do pieczenia i sól, wymieszaj do uzyskania jednolitej masy.\n 4. Dodaj posiekane orzechy i delikatnie wymieszaj.\n 5. Przełóż ciasto do formy.\n 6. Wstaw do piekarnika na 180 stopni na 30 minut.\n 7. Po upieczeniu, ostudź przed podaniem.", 50, 8, "trudny");
        recipes[5] = new RecipeObject("Tiramisu", "serek mascarpone 500g,cukier 100g,jajka 100g,kawa 200g,biszkopty 200g,kakao 10g,likier 50g,sól 5g", " 1. Ubij jajka z cukrem na puszystą masę.\n 2. Dodaj serek mascarpone i delikatnie wymieszaj.\n 3. Namocz biszkopty w kawie i likierze.\n 4. Układaj warstwami biszkopty i krem mascarpone.\n 5. Posyp wierzch kakao.\n 6. Wstaw do lodówki na 4 godziny przed podaniem.", 300, 6, "trudny");
    }

    @FXML
    private VBox boxForProduct;
    @FXML
    private VBox boxForProductPantry;
    @FXML
    private TextField newProduct;
    @FXML
    private TextField productAmount;
    @FXML
    private Button acceptButton;
    @FXML
    private Label firstIngredient;
    @FXML
    private Label secondIngredient;
    @FXML
    private Label thirdIngredient;
    @FXML
    private Label fourthIngredient;
    @FXML
    private Label fifthIngredient;
    @FXML
    private Label sixthIngredient;
    @FXML
    private Label seventhIngredient;
    @FXML
    private Label eighthIngredient;
    @FXML
    private Label time;
    @FXML
    private Label whatRecipe;
    @FXML
    private Label actualRecipe;
    @FXML
    private Label portions;
    @FXML 
    private Label difficulty;
    @FXML
    private VBox recipeListBox;
    @FXML
    private Button makeCake;
    @FXML
    public void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }


    @FXML
    public void switchToSpizarnia() throws IOException {
        App.setRoot("spizarnia");
    }


    @FXML
    public void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void switchToRecipe() throws IOException {
        App.setRoot("recipe");
    }

    @FXML
    public void initialize() {
        if (boxForProduct != null) {
            fastInit();
        } else {
        }
        if (boxForProductPantry != null) {
            fastInit2();
        }
        if (firstIngredient != null) {
            pickedRecipe = App.getPickedRecipe();
            initializeRecipe();
        }
    }

    @FXML
    private void switchToRecipe(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();
        int recipeNumber = Integer.parseInt((String) clickedButton.getUserData());
        App.setPickedRecipe(recipes[recipeNumber]);
        App.setRoot("recipe");
    }

    private void initializeRecipe() {
        firstIngredient.setText(pickedRecipe.getIngredient(0));
        secondIngredient.setText(pickedRecipe.getIngredient(1));
        thirdIngredient.setText(pickedRecipe.getIngredient(2));
        fourthIngredient.setText(pickedRecipe.getIngredient(3));
        fifthIngredient.setText(pickedRecipe.getIngredient(4));
        sixthIngredient.setText(pickedRecipe.getIngredient(5));
        seventhIngredient.setText(pickedRecipe.getIngredient(6));
        eighthIngredient.setText(pickedRecipe.getIngredient(7));
        time.setText(Integer.toString(pickedRecipe.getPreparationTime()) + " minut");
        whatRecipe.setText(pickedRecipe.getName());
        actualRecipe.setText(pickedRecipe.getInstructions());
        portions.setText(Integer.toString(pickedRecipe.getServings()));
        difficulty.setText(pickedRecipe.getDiff());
        if(RecipeObject.checkPantryforRecipe(pickedRecipe.getIngredients())) {
            makeCake.setText("Wykonaj ciasto");
            makeCake.setDisable(false);     


        } else {
            makeCake.setText("Czy mam składniki?");
            makeCake.setDisable(false);
        }
    }
    @FXML
    private void makeCake(){
        StringBuilder missing = new StringBuilder();
        for(String ingredient : pickedRecipe.getIngredients()){
            String removeIngredient = ingredient.substring(0, ingredient.lastIndexOf(" "));
            int removeValue = Integer.parseInt(ingredient.substring(ingredient.lastIndexOf(" ")+1, ingredient.length()-1));
            int currentAmount = Pantry.getCurrentAmount(removeIngredient); // Implement getCurrentAmount in Pantry if needed

            if (currentAmount < removeValue) {
                missing.append(removeIngredient)
                       .append(" (potrzebne: ").append(removeValue)
                       .append(", dostępne: ").append(currentAmount).append(")\n");
            }
        }
        if (missing.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Brakuje składników");
            alert.setHeaderText(null);
            alert.setContentText("Nie masz wystarczających składników:\n" + missing.toString());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wykonanie przepisu");
            alert.setHeaderText(null);
            alert.setContentText("Czy wykonałeś przepis?");
            ButtonType takButton = new ButtonType("Tak", ButtonBar.ButtonData.OK_DONE);
            ButtonType nieButton = new ButtonType("Nie", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(takButton, nieButton);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == takButton) {
                StringBuilder leftover = new StringBuilder();
                for(String ingredient : pickedRecipe.getIngredients()){
                    String removeIngredient = ingredient.substring(0, ingredient.lastIndexOf(" "));
                    int removeValue = Integer.parseInt(ingredient.substring(ingredient.lastIndexOf(" ")+1, ingredient.length()-1));
                    Pantry.subtractProductAmount(removeIngredient, removeValue);
                    leftover.append(removeIngredient)
                            .append(" - ")
                            .append(Pantry.getCurrentAmount(removeIngredient)).append("g\n");
                }
                Alert leftoverAlert = new Alert(Alert.AlertType.INFORMATION);
                leftoverAlert.setTitle("Stan składników");
                leftoverAlert.setHeaderText(null);
                leftoverAlert.setContentText("Po wykonaniu przepisu zostało ci:\n" + leftover.toString());
                leftoverAlert.showAndWait();
            }
        }
    }
    private void fastInit() {
        for (int i = 0; i < Database.getAllProductsMatrix().length; i++) {
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
            Pantry.insertOrUpdateProduct(productLabel.getText(), Integer.parseInt(dataProductAmount));
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
        Pantry.createTable();
        HBox newHbox = new HBox(8);
        newHbox.setAlignment(Pos.CENTER);
        newHbox.setTranslateX(4);

        Label newProductLabel = new Label(newProduct.getText());
        newProductLabel.setFont(new Font("Arial", 24));
        newProductLabel.setPrefWidth(332);
        newProductLabel.setAlignment(Pos.CENTER);

        String productAmountText = productAmount.getText();
        if (!productAmountText.matches("\\d+") || Integer.parseInt(productAmountText) <= 0) {
            System.out.println("Invalid product amount: " + productAmountText);
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Invalid Input");
            alert.setHeaderText(null);
            alert.setContentText("Invalid product amount: " + productAmountText);
            alert.showAndWait();
            return;
        }

        Label productAmountLabel = new Label(productAmountText + "g");
        productAmountLabel.setFont(new Font("Arial", 24));
        productAmountLabel.setPrefWidth(200);
        productAmountLabel.setAlignment(Pos.CENTER);
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 24));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            Pantry.insertOrUpdateProduct(newProductLabel.getText(), Integer.parseInt(productAmountLabel.getText().substring(0, productAmountLabel.getText().length() - 1)));
            boxForProduct.getChildren().remove(newHbox);
            Database.deleteProduct(newProductLabel.getText());
        });
        Button deleteProduct = new Button("X");
        deleteProduct.setFont(new Font("Arial", 24));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(newHbox);
            Database.deleteProduct(newProductLabel.getText());
        });

        newHbox.getChildren().addAll(newProductLabel, productAmountLabel, boughtProduct, deleteProduct);
        boxForProduct.getChildren().add(newHbox);
        if (productAmount.getText().isEmpty()) {
            boxForProduct.getChildren().remove(newHbox);
        } else {
            Database.insertProduct(newProduct.getText(), productAmount.getText());
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

    @FXML
    private void fastInit2() {
        for (int i = 0; i < Pantry.getAllProductsMatrix2().length; i++) {
            System.out.print(Pantry.getAllProductsMatrix2()[i][1] + " " + Pantry.getAllProductsMatrix2()[i][2]);
            String productNamePandry = Pantry.getAllProductsMatrix2()[i][1];
            String productAmountPandry = Pantry.getAllProductsMatrix2()[i][2];
            initializeFromDatabase2(productNamePandry, productAmountPandry);
        }
    }

    @FXML
    private void initializeFromDatabase2(String dataProductName, String dataProductAmount) {
        HBox productBoxPandry = new HBox(8);
        productBoxPandry.setAlignment(Pos.CENTER_LEFT);
        productBoxPandry.setTranslateX(8);

        Label productLabelPandry = new Label(dataProductName);
        productLabelPandry.setFont(new Font("Arial", 24));
        productLabelPandry.setPrefWidth(332);
        productLabelPandry.setAlignment(Pos.CENTER);

        Label amountLabel = new Label(dataProductAmount + "g");
        amountLabel.setFont(new Font("Arial", 24));
        amountLabel.setPrefWidth(270);
        amountLabel.setAlignment(Pos.CENTER);

        productBoxPandry.getChildren().addAll(productLabelPandry, amountLabel);
        boxForProductPantry.getChildren().add(productBoxPandry);
    }

    @FXML
    public void displayRecipes() {
        recipeListBox.getChildren().clear();
        for (int i = 0; i < recipes.length; i++) {
            Button recipeButton = new Button(recipes[i].getName());
            recipeButton.setUserData(String.valueOf(i));
            recipeButton.setOnAction(event -> {
                try {
                    switchToRecipe(event);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            recipeListBox.getChildren().add(recipeButton);
        }
    }

    @FXML
    private void clearPantryDatabase() {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Czy na pewno chcesz wyczyścić spiżarnię?", ButtonType.YES, ButtonType.NO);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText(null);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Pantry.clearDatabase2();
            boxForProductPantry.getChildren().clear(); // Clear the view
            fastInit2(); // Reinitialize the view
            Alert infoAlert = new Alert(AlertType.INFORMATION, "Spiżarnia została wyczyszczona.");
            infoAlert.setTitle("Informacja");
            infoAlert.setHeaderText(null);
            infoAlert.showAndWait();
        }
    }
    
}
