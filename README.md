[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2111/gr2111)
# Salary Checker :computer:

This repo contains a multi-module, multi-package javafx project for checking salary. This application is mainly meant for people who work on commision. 
The app will calculate if you have been paid the correct amount based on your sales. You can read more about the project here: [Salary Checker](salary-checker/README.md).

## Building and running the project

1. `mvn clean install` | tells Maven to do the clean phase in each module before running the install phase for each module. In addition all the tests will also be run.
     
     If you want to skip the tests: `mvn clean install -Dskiptests`
2. `mvn -pl ui javafx:run` | tells Maven to run the app from the UI module. 

## Organization of the code:

- [salary-checker](salary-checker/)
    - [core](salary-checker/core)
        - [domain-logic](salary-checker/core/src/main/java/salarychecker/core)
        - [persistence-layer](salary-checker/core/src/main/java/salarychecker/json)
    - [ui](salary-checker/ui)
        - [javafx](salary-checker/ui/src/main/java/salarychecker/ui)
        - [FXML](salary-checker/ui/src/main/resources)

[Read more about the modules and the code](salary-checker/README.md)
    
## The plan

**Read more about the project here:** [Salary-Checker](salary-checker/README.md)

The project is set to three releases.

#### <ins> Sprint 1 </ins>

This sprint contains two user stories: us-1 and us-2.

**US-1:** As an already registered user, I want to log in to SalaryChecker.

**US-2:** As a user I want to see the functionality in the app, after I have given my correct credentials.

**Read more about it here:** [Release 1](docs/release1/README.md)

#### <ins> Sprint 2 </ins>

In this sprint we will continue to add more functionality. The goal is that after this sprint the app should be almost complete with wanted functionality.
**US-3:** As a user, I want to change my profile information.

**US-4:** As a user, I want to check what my expected salary is and what the difference is.

**US-5:** As a user, I want to see previously calculated salaries.

**US-6:** As a user, I want to send an email to the employer. | *This user story can be changed or pushed to sprint 3.*

**Read more about it here:** [Release 2](docs/release2/) 

#### <ins> Sprint 3 </ins>

In this sprint we will work on REST API and REST SERVER. If we have more time we will work on styling of the application and add another user story

<ins>*Bonus:*</ins>

   *US-7:* As a user, I want to enter my working hours.
   
**Read more about it here:** [Release 3](docs/release3/)

_____________________________________________________________

*This plan is tentative, so the plan can be changed as we develop. The finished product for the sprint will be documented in 
the respective release folders.* 

