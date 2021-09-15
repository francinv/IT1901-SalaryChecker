[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2111/gr2111)
# Salary Checker

This repo contains a multi-module, multi-package javafx project for checking salary. This application is mainly meant for people who work on commision. 
The app will calculate if you have been paid the correct amount based on your sales. You can read more about the project here: [Salary Checker](docs/README.md).

## Building and running the project

1. `mvn clean install` | tells Maven to do the clean phase in each module before running the install phase for each module. In addition all the tests will also be run.
     
     If you want to skip the tests: `mvn clean install -Dskiptests`
2. `mvn -pl ui javafx:run` | tells Maven to run the app from the UI module. 

## Organization of the code:

- [salary-checker](salary-checker/)
    - [core](salary-checker/core)
        - [domain-logic](salary-checker/core/src/main/java/core)
        - [persistence-layer](salary-checker/core/src/main/java/json)
    - [ui](salary-checker/ui)
        - [javafx](salary-checker/ui/src/main/java/ui)
        - [FXML](salary-checker/ui/src/main/resources)

[Read more about the modules and the code](salary-checker/README.md)
    
## The plan

**Read more about the project here:** [Salary-Checker](docs/README.md)

The project is set to three releases.

####Sprint 1

This sprint contains two user stories: us-1 and us-2.

**US-1:** As an already registered user, I want to log in to SalaryChecker.

**US-2:** As a user I want to see the functionality in the app, after I have given my correct credentials.


