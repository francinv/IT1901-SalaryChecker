module salarychecker.core {
    requires com.opencsv;
    requires java.sql;
    requires javax.mail;
    exports salarychecker.core;
    opens salarychecker.core;
}
