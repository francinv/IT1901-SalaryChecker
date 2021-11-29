[![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-Ready--to--Code-blue?logo=gitpod)](https://gitpod.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2021/gr2111/gr2111)
# Salary Checker :computer:

This repo contains a multi-module, multi-package javafx project for checking salary. This application is mainly meant for people who work on commision. 
The app will calculate if you have been paid the correct amount based on your sales. You can read more about the project here: [Salary Checker](salary-checker/README.md).


## Building and running the project:

The application can be ran in three different ways. A step by step guide on how to perform each way is provided below.

#### Note:
This project is built up with maven and subsequently, if maven is not installed, one needs to use the **./mvnw** command instead of **mvn**. This does not apply if the application is ran from gitpod.  

Furthermore, only admin-users added by developers can use the application as admins.

Due to the size of the UI-test the app seemlingly stops building at random times. If this happens UI-tests can be skipped.
See bottom of page.



### Running application with local access:
1) Navigate to salary-checker directory.
2) Install dependencies and run tests.
3) Run javafx client application.

```sh
1)
cd salary-checker

2)
mvn clean install
#Tests can be skipped by using "mvn clean install -DskipTests"
#Clean install can be skipped if application has been ran with remote access or with react client previously.

3)
mvn javafx:run -pl ui
```
### Running application with remote access:
1) Navigate to salary-checker directory.
2) Install dependencies and run tests.
3) Start server. 
3.5) Open new terminal window.
4) Run javafx client application.

```sh
1)
cd salary-checker

2)
mvn clean install
#Tests can be skipped by using "mvn clean install -DskipTests"
#Clean install can be skipped if application has been ran with local access or with react client previously.

3)
mvn spring-boot:run -pl restserver

#Command 4 must be written in seperate terminal window after repeating step 1)

4)
mvn javafx:run -pl ui -P remoteapp
```
### Running application with react client:
1) Navigate to salary-checker directory.
2) Install dependencies and run tests.
3) Start server.
3) Run react client application.

```sh
1)
cd salary-checker

2)
mvn clean install
#Tests can be skipped by using "mvn clean install -DskipTests"
#Clean install can be skipped if application has been ran with remote access or with local access previously.

3)
mvn spring-boot:run -pl restserver

4)
https://salarycheckergr2111.herokuapp.com/
#Click link above.
```
## How to get started using SalaryChecker:
The SalaryChecker application is meant for two seperate user-groups. 
Employers, from this point on referenced as admin in the guide, 
and Employees, from this point on referneced as user in the guide. 

A user is meant to be able to calculate his salary by uploading a sales report and view his past salares.

An admin is meant to be able to create users and have an overview of all users using the application.

### Before logging in: 
Create test users by clicking the "Create test users" button. 
This will create two test users. One **Admin** and one **User**. 

### Log in: 
Whohever uses the application is now able to log in with the test-credentials.

Logging in as a user: 
E-Mail: test@live.no
Password: Password123!

Logging in as an admin: 

E-Mail: test@admin.no
Password: Password123!

Both methods will lead to a start page. From here both a user and admin can navigate to their respective 
functionality via the menu to the left. 

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

**US-6:** As a user, I want to send an email to the employer. | *This user story can be changed or pushed to sprint 3.* |  

**Read more about it here:** [Release 2](docs/release2/) 

#### <ins> Sprint 3 </ins>

In this sprint we will work on REST API and REST SERVER. If we have more time we will work on styling of the application and add another user story

<ins>*Bonus:*</ins>

   *US-7:* As a user, I want to enter my working hours.
   
**Read more about it here:** [Release 3](docs/release3/)

_____________________________________________________________

*This plan is tentative, so the plan can be changed as we develop. The finished product for the sprint will be documented in 
the respective release folders.* 



### Continuation from Note:

The application is able to build, but not consistently, due to the size of UI-tests.

[Picture](docs/Pictures/BuilSuccess.jpg)
