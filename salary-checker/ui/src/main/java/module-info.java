module salarychecker.ui {
    requires salarychecker.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires salarychecker.json;
    opens salarychecker.ui to javafx.graphics, javafx.fxml;
}
