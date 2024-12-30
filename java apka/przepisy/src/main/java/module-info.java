module ciasta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens ciasta to javafx.fxml;
    exports ciasta;
}
