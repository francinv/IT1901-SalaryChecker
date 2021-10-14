module salarychecker.core {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.opencsv;
    requires java.sql;
    requires javax.mail;
    exports salarychecker.core;
    exports salarychecker.json;
    opens salarychecker.core;
}
