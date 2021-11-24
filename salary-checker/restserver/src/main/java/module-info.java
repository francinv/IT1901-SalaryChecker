module salarychecker.restserver {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires spring.core;
  requires spring.context;
  requires spring.data.rest.core;
  requires spring.data.commons;
  requires spring.web;
  requires spring.webmvc;

  requires transitive salarychecker.core;
  requires salarychecker.json;
  requires com.fasterxml.jackson.databind;

  opens salarychecker.restserver to spring.beans, spring.context, spring.web, spring.core;
  exports salarychecker.restserver.properties to spring.beans, spring.boot;
}