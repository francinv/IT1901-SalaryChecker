module salarychecker.ui {
    requires transitive javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.net.http;

    requires transitive salarychecker.core;
    requires transitive salarychecker.json;

    opens salarychecker.ui to javafx.graphics, javafx.fxml;
    opens salarychecker.ui.controllers to javafx.graphics, javafx.fxml;
    exports salarychecker.ui.controllers;
}
