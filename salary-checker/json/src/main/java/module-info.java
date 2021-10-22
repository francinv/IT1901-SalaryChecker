module salarychecker.json {
    requires transitive com.fasterxml.jackson.core;
    requires transitive com.fasterxml.jackson.databind;
    requires transitive salarychecker.core;
    exports salarychecker.json;
    opens salarychecker.json;
}