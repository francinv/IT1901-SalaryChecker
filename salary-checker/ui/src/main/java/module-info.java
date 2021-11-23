module salarychecker.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    requires transitive salarychecker.core;
    requires transitive salarychecker.json;

    opens salarychecker.ui to javafx.graphics, javafx.fxml;
    opens salarychecker.ui.controllers to javafx.graphics, javafx.fxml;
    exports salarychecker.ui.controllers;
}
