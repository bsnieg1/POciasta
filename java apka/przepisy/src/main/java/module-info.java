module ciasta {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    
    opens ciasta to javafx.fxml;
    exports ciasta;
}
