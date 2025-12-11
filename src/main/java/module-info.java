module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com to javafx.fxml;
    opens com.controller to javafx.fxml;
    opens com.model to javafx.fxml;
    opens com.database to java.sql;
    exports com;
    exports com.controller;
    exports com.model;
    exports com.database;
}
