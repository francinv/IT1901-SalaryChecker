module salarychecker.ui {
    requires transitive javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires java.net.http;
    requires methanol;

    requires transitive salarychecker.core;
    requires transitive salarychecker.json;

    exports salarychecker.ui to javafx.graphics;

    opens salarychecker.ui.controllers to javafx.graphics, javafx.fxml;
    exports salarychecker.ui.controllers;

    opens salarychecker.dataaccess;
    exports salarychecker.dataaccess;
}
