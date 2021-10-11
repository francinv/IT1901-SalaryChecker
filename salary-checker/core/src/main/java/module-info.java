module salarychecker.core {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires com.opencsv;
    requires java.sql;
    exports salarychecker.core;
    exports salarychecker.json;
}
