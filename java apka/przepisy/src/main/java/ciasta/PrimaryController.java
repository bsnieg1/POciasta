package ciasta;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
//


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
        App.setRoot("secondary"); // Load the secondary scene
        // Fetch all products from the database and add them to the VBox
        //fastInit();   
    }
    @FXML
    public void initialize() {
        if (boxForProduct != null) {
            System.out.println("VBox initialized correctly." + boxForProduct);
        } else {
            System.out.println("VBox is not initialized yet." + boxForProduct);
        }
    }

    private void fastInit() {
        for (int i = 0; i < Database.getAllProductsMatrix().length; i++) {
            System.out.print(Database.getAllProductsMatrix()[i][1] + " " + Database.getAllProductsMatrix()[i][2]);
            String productName = Database.getAllProductsMatrix()[i][1]; // Get product name
            String productAmount = Database.getAllProductsMatrix()[i][2]; // Get product amount
            initializeFromDatabase(productName, productAmount); // Add to the UI
        }
    }
    private void initializeFromDatabase(String dataProductName, String dataProductAmount) {
        // Create an HBox to hold the product name and amount
        HBox productBox = new HBox(8);  // Spacing of 8 between elements
        productBox.setAlignment(Pos.CENTER_LEFT); // Align elements to the left
        
        // Create a Label for the product name
        Label productLabel = new Label(dataProductName);
        productLabel.setFont(new Font("Arial", 24)); // Set font for visibility
        productLabel.setPrefWidth(332);
        productLabel.setAlignment(Pos.CENTER_LEFT);
        // Create a Label for the product amount
        Label amountLabel = new Label(dataProductAmount);
        amountLabel.setFont(new Font("Arial", 24));
        amountLabel.setPrefWidth(200);
        amountLabel.setAlignment(Pos.CENTER);
        
        // Create the "bought" and "delete" buttons
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 22));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            // Remove the product from the UI and database when "bought"
            boxForProduct.getChildren().remove(productBox); 
            Database.deleteProduct(dataProductName);
        });
    
        Button deleteProduct = new Button("X");
        deleteProduct.setFont(new Font("Arial", 22));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> {
            // Remove the product from the UI and database when "delete"
            boxForProduct.getChildren().remove(productBox);
            Database.deleteProduct(dataProductName);
        });
    
        // Add labels and buttons to the HBox
        productBox.getChildren().addAll(productLabel, amountLabel, boughtProduct, deleteProduct);
    
        // Add the HBox to the VBox (boxForProduct)
        boxForProduct.getChildren().add(productBox);
    }

    @FXML
    private void add() {
        Database.createTable();

        HBox newHbox = new HBox(8);  
        newHbox.setAlignment(Pos.CENTER);


        Label newProductLabel = new Label(newProduct.getText());  
        newProductLabel.setFont(new Font("Arial", 24)); 
        newProductLabel.setPrefWidth(332);
        newProductLabel.setAlignment(Pos.CENTER_LEFT);

        Label productAmountLabel = new Label(productAmount.getText() + "g"); 
        productAmountLabel.setFont(new Font("Arial", 24));  
        productAmountLabel.setPrefWidth(200);
        productAmountLabel.setAlignment(Pos.CENTER);
        Button boughtProduct = new Button("✓");
        boughtProduct.setFont(new Font("Arial", 22));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(newHbox);  // zmienic pozniej zeby dodawac do drugiej bazy danych
            Database.deleteProduct(newProductLabel.getText());
        });
        Button deleteProduct = new Button("X");
        deleteProduct.setFont(new Font("Arial", 22));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> {
            boxForProduct.getChildren().remove(newHbox);
            Database.deleteProduct(newProductLabel.getText());
        });
        //Dodajemy trzy Labelki do HBox
        newHbox.getChildren().addAll(newProductLabel, productAmountLabel,boughtProduct, deleteProduct);
        boxForProduct.getChildren().add(newHbox);
        if (productAmount.getText().isEmpty()){ 
            boxForProduct.getChildren().remove(newHbox); 
        }else{
            Database.insertProduct(newProduct.getText(), productAmount.getText()+ "g");
        }
        
 
       
        

   
        try (ResultSet rs = Database.getProducts()) {
            while (rs != null && rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Product: " + rs.getString("product_name") + ", Amount: " + rs.getString("product_amount"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving products: " + e.getMessage());
        }

        
        fastInit();
        newProduct.clear();
        productAmount.clear();
    }
}