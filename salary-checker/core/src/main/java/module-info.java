module salarychecker.core {
    requires com.opencsv;
    requires java.sql;
    exports salarychecker.core;
    opens salarychecker.core;
}
