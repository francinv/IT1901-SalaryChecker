module salarychecker.json {
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires salarychecker.core;
    exports salarychecker.json;
    opens salarychecker.json;
}