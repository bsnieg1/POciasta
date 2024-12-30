package ciasta;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToSpizarnia() throws IOException {
        App.setRoot("spizarnia");
    }

    @FXML
    private VBox boxForProduct;  // VBox, do którego będą dodawane nowe Labelki

    @FXML
    private TextField newProduct;  // Istniejące pole tekstowe w FXML

    @FXML
    private TextField productAmount;  // Istniejące pole tekstowe w FXML

    @FXML
    private Button acceptButton;  // Przycisk "Dodaj"

    // Metoda obsługująca kliknięcie przycisku
    @FXML
    private void add() {

        // Tworzymy nowy HBox, w którym będą umieszczone nowe Labelki
        HBox newHbox = new HBox(8);  // Tworzymy HBox z odstępem 10 pikseli
        newHbox.setAlignment(Pos.CENTER);

        // Tworzymy nową etykietę dla nazwy produktu
        Label newProductLabel = new Label(newProduct.getText());  // Pobieramy tekst z pola "newProduct"
        newProductLabel.setFont(new Font("Arial", 24));  // Czcionka
        newProductLabel.setPrefWidth(332);
        newProductLabel.setAlignment(Pos.CENTER_LEFT);
        // Tworzymy nową etykietę dla ilości produktu
        Label productAmountLabel = new Label(productAmount.getText() + " g");  // Pobieramy tekst z pola "productAmount"
        productAmountLabel.setFont(new Font("Arial", 24));  // Czcionka
        productAmountLabel.setPrefWidth(200);
        productAmountLabel.setAlignment(Pos.CENTER);
        Button deleteProduct = new Button("✓");
        deleteProduct.setFont(new Font("Arial", 23));
        deleteProduct.setAlignment(Pos.CENTER_RIGHT);
        deleteProduct.setOnAction(e -> boxForProduct.getChildren().remove(newHbox));

        Button boughtProduct = new Button("X");
        boughtProduct.setFont(new Font("Arial", 23));
        boughtProduct.setAlignment(Pos.CENTER_RIGHT);
        boughtProduct.setOnAction(e -> boxForProduct.getChildren().remove(newHbox));
        // Dodajemy trzy Labelki do HBox
        newHbox.getChildren().addAll(newProductLabel, productAmountLabel,deleteProduct, boughtProduct);
        boxForProduct.getChildren().add(newHbox);
        if (productAmount.getText().isEmpty()){  // Sprawdzamy, czy pole jest puste (ignorując białe znaki)
            boxForProduct.getChildren().remove(newHbox); // Usuwamy newHbox z kontenera
        }
        
 
        // Dodajemy nowy HBox do VBox (który przechowuje wszystkie elementy)
        
        
        // Opcjonalnie czyść pola tekstowe po dodaniu:
        newProduct.clear();
        productAmount.clear();
    }
}
