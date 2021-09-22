module salarychecker.ui {
    requires salarychecker.core;
    requires javafx.controls;
    requires javafx.fxml;

    opens salarychecker.ui to javafx.graphics, javafx.fxml;
}
