package ciasta;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
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



public class PrimaryController {

    private RecipeObject pickedRecipe;
    private RecipeObject[] recipes = new RecipeObject[6];
    public PrimaryController(){
        recipes[0] = new RecipeObject("Ciasto marchewkowe", "marchewki 100g, mąka 100g, cukier 50g, jajka 100g, olej 10g, proszek do pieczenia 5g, cynamon 5g, sól 5g", " 1. Wymieszaj suche składniki.\n 2. Dodaj mokre składniki.\n 3. Wymieszaj.\n 4. Wstaw do piekarnika na 180 stopni na 45 minut.", 45, 12);
        recipes[1] = new RecipeObject("Sernik", "ser 500g, cukier 200g, jajka 100g, masło 100g, mąka 50g, proszek do pieczenia 5g, wanilia 5g, sól 5g", " 1. Wymieszaj ser z cukrem.\n 2. Dodaj jajka i masło.\n 3. Dodaj mąkę i proszek do pieczenia.\n 4. Wymieszaj.\n 5. Wstaw do piekarnika na 180 stopni na 60 minut.", 60, 8);
        recipes[2] = new RecipeObject("Szarlotka", "jabłka 500g, mąka 300g, cukier 200g, masło 200g, jajka 100g, proszek do pieczenia 5g, cynamon 5g, sól 5g", " 1. Wymieszaj mąkę z proszkiem do pieczenia.\n 2. Dodaj masło i jajka.\n 3. Wymieszaj.\n 4. Dodaj pokrojone jabłka i cynamon.\n 5. Wstaw do piekarnika na 180 stopni na 50 minut.", 50, 10);
        recipes[3] = new RecipeObject("Makowiec", "mak 200g, mąka 300g, cukier 150g, jajka 100g, masło 100g, proszek do pieczenia 5g, mleko 100ml, sól 5g", " 1. Wymieszaj mąkę z proszkiem do pieczenia.\n 2. Dodaj masło, jajka i mleko.\n 3. Wymieszaj.\n 4. Dodaj mak i cukier.\n 5. Wstaw do piekarnika na 180 stopni na 60 minut.", 60, 10);
        recipes[4] = new RecipeObject("Brownie", "czekolada 200g, masło 100g, cukier 150g, jajka 100g, mąka 50g, proszek do pieczenia 5g, sól 5g, orzechy 50g", " 1. Roztop czekoladę z masłem.\n 2. Dodaj cukier i jajka.\n 3. Dodaj mąkę i proszek do pieczenia.\n 4. Wymieszaj.\n 5. Dodaj orzechy.\n 6. Wstaw do piekarnika na 180 stopni na 30 minut.", 30, 8);
        recipes[5] = new RecipeObject("Tiramisu", "serek mascarpone 500g, cukier 100g, jajka 100g, kawa 200ml, biszkopty 200g, kakao 10g, likier 50ml, sól 5g", " 1. Ubij jajka z cukrem.\n 2. Dodaj serek mascarpone.\n 3. Namocz biszkopty w kawie i likierze.\n 4. Układaj warstwami biszkopty i krem.\n 5. Posyp kakao.\n 6. Wstaw do lodówki na 4 godziny.", 240, 6);
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
            System.out.println("VBox initialized correctly." + boxForProduct);
            fastInit();
        } else {
            System.out.println("VBox is not initialized yet." + boxForProduct);
        }
         if (boxForProductPantry != null) {
             System.out.println("VBox2 initialized correctly." + boxForProductPantry);
             fastInit2();
         } else {
             System.out.println("VBox2 is not initialized yet." + boxForProductPantry);
         }
         if(firstIngredient!=null){
                pickedRecipe = App.getPickedRecipe();
                System.out.println("Recipe initialized correctly." + firstIngredient);
                //pickedRecipe = new RecipeObject("Ciasto marchewkowe", "marchewki 100g, mąka 100g, cukier 50g, jajka 100g, olej 10g, proszek do pieczenia 5g, cynamon 5g, sól 5g", "1. Wymieszaj suche składniki. 2. Dodaj mokre składniki. 3. Wymieszaj. 4. Wstaw do piekarnika na 180 stopni na 45 minut.", 45, 12);
                initializeRecipe();
        }else {
            System.out.println("xddd is not initialized yet." + firstIngredient);
        }
    }    
    @FXML
    private void switchToRecipe(ActionEvent event) throws IOException {
        Button clickedButton = (Button) event.getSource();  // Get the clicked button
        int recipeNumber = Integer.parseInt((String) clickedButton.getUserData());  // Get the userData value
        App.setPickedRecipe(recipes[recipeNumber]); 
        App.setRoot("recipe");
    }

    

    private void initializeRecipe() {
        //RecipeObject pickedRecipe = new RecipeObject("Ciasto marchewkowe", "marchewki 100g, mąka 100g, cukier 50g, jajka 100g, olej 10g, proszek do pieczenia 5g, cynamon 5g, sól 5g", "1. Wymieszaj suche składniki. 2. Dodaj mokre składniki. 3. Wymieszaj. 4. Wstaw do piekarnika na 180 stopni na 45 minut.", 45, 12);
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
    }
    
    private void fastInit() {
        for (int i = 0; i < Database.getAllProductsMatrix().length; i++) {
            //System.out.print(Database.getAllProductsMatrix()[i][1] + " " + Database.getAllProductsMatrix()[i][2]);
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

        Label productAmountLabel = new Label(productAmount.getText() + "g"); 
        productAmountLabel.setFont(new Font("Arial", 24));  
        productAmountLabel.setPrefWidth(200);
        productAmountLabel.setAlignment(Pos.CENTER);
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 24));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            Pantry.insertOrUpdateProduct(newProductLabel.getText(), Integer.parseInt(productAmountLabel.getText().substring(0, productAmountLabel.getText().length()-1)));
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
        amountLabel.setPrefWidth(200);
        amountLabel.setAlignment(Pos.CENTER);
        
    
        productBoxPandry.getChildren().addAll(productLabelPandry, amountLabel);
    
        boxForProductPantry.getChildren().add(productBoxPandry);
    }
    
}