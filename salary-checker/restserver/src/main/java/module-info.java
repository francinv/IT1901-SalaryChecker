module salarychecker.restserver {
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.beans;
  requires spring.web;

  requires transitive salarychecker.core;
  requires salarychecker.json;
  requires spring.context;
  requires spring.data.rest.core;
  requires spring.data.commons;
  requires spring.core;

  opens salarychecker.restserver;
  exports salarychecker.restserver;
}