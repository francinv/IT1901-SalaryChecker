# RestServer
>The group chose to implement the Springboot-framework in order to set up the rest-server. Although other alternatives were considered, the group ultimately decided on springboot. Some of Springboots features that led to this desition are: Springboot makes it quick and easy to create stand alone applications, it has Tomcat, Jetty and Undertow embeded so no WAR-file is required, and minimal code generation and no requirement for XML configuration. Ultimately those were some of the conveniences that led the group to using Spring boot instead of creating a rest server manually. The Rest Server module consists of the following classes: 

- RestServerApplication.java, which contains the start-method for the server application.
- RestServerController.java, which makes it possible for the server to listen for HTTP-requests and process them accordingly.
- RestServerService.java, which is used by the controller in order to handle User-objects requested by the client.

Additionally Spring boot has robust serialization and deserialization, of Plain Old Java Objects (POJOs) using Jackson under the hood.