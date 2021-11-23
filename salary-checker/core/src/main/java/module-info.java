module salarychecker.core {
    requires java.sql;
    requires commons.csv;
    exports salarychecker.core;
    opens salarychecker.core;
}
