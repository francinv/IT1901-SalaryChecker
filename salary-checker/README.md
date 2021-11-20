# Salary Checker

## Motivation and purpose for the project:
Many people are employed in the sales industry, and a lot of companies reward their sales-employees based on a bonus scheeme. Payouts can vary based on what products were sold, the employees rank within the company, if the costumer was a new or previous costumer etc. All these factors can make it har to keep track of what is owed to an employee at the end of the month. The SalaryChecker application attempts to solve this problem for the employee.

## Userstories
#### Userstories for later releases will be added as functionality improves during later releases
### Userstory release 1:
    "An employee at a salesfirm implementing the Salarychecker application wants to check his userprofile, and verify that his personal information is correct. He therefore loggs in to Salarychecker and looks at his profile page to view his personal information."
### Userstory release 2:
#### Story 1:
    "The previously logged in employee wishes to verify that his last paycheck was indeed correct. He opens the "Lønnsutregniger" tab and enters the amount he/she was payed. The employee proceeds to enter her total hours worked and uploads his/her sales report for the previous month.

    The employee notices a difference in amount that has been payed and what he/she is really owed. The employee wants to notify his/her employer via email. The employee navigates to the E-Mail tab, enters what month the complaint is about, and sends an E-Mail to the employer."
#### Story 2:
    "An emplyee feels like he/she is on a positive trend in terms of salary. He/She wishes to compare her last months sales to previous ones. She navigates to the "Tidligere lønninger" tab and examines the overview of her previous paychecks.

#### Story 3: 
    "After realising the positive trend in salary the emplyee notices she will enter a different tax bracket than he/she was previously in. He/She wishes to update his/her profile accordingly. The employee also realizes his/her password has not been changed in a while and wishes to update this as well. The employee opens the settings window where he/she can make the previously mentioned changes."  

### Userstory release 3:
     "An employer wishes to check if he has made any errors regarding paychecks. He loggs into the client-application, and gets an overview of users. He checks every indiviual user and checks for differences in expected and actual payout."

## Intended functionality at final release:
### For employee (User): 
#### - View his/her personal profile page where he/she gets an overview of the following information:
    - Full name
    - E-mail
    - Employee-number/id 
    - Birthdate
    - His/her current tax-bracket 
    - Possibility to change password/e-mail

#### - View the calculation/basis of what salary he/she is owed for current and previous months:
    - A list of all sales with corresponding bonuses
    - A list of all hours worked with corresponding hourly wage
    - Sum of bonuses
    - Sum of hourly wages for all hours worked
    - Final salary 

#### - List of hours worked for current and previous month
    - Hours will be marked differently based on wether it is a regular working day, overtime, weekend, holiday

#### - List of previous payouts
    - Monthly payout before tax
    - Monthly payout after tax

#### - Upload all sales and working hours from a .csv file

#### - Send email to employer directly from the SalaryApplication
    - This functionality is included to simplify the process of complaint in case of an error between salary recieved and salary owed.

### For Employer (Admin):
#### - Create and delete profiles for employees.
    - Admin will initiate profile with Name, E-mail, Password, taxbracket, and Social security number (Can be changed by user later, except for Social securitynumber.).


## Project structure:
### Project structure will be updated after later releases.
![Architecture](../docs/ARCHITECTURE.png)

- [docs](../../docs)
    - [release1](../../docs/release1)
        - [README.md](../../docs/release1/README.md) :point_right: Documentation for release 1
    - [release2](../../docs/release2)
        - [README.md](../../docs/release2/README.md) :point_right: Documentation for release 2
    - [release3](../../docs/release3)
        - [README.md](../../docs/release3/README.md) :point_right: Documentation for release 3


- [salary-checker](../salary-checker)
    - [core](./core)
        - [salarychecker/core](./core/src/main/java/salarychecker/core)
            - [AbstractUser](.cor/src/main/java/salarychecker/core/AbstractUser.java)
            - [User](./core/src/main/java/salarychecker/core/User.java) :point_right: Creates a new user, get and set methods for all User-attributes.
            - [AdminUser](./core/src/main/java/salarychecker/core/AdminUser.java) :point_right: Creates new admin user.
            - [Calculation](./core/src/main/java/salarychecker/core/Calculation.java) :point_right: Calculates salary for a given user and sales period.
            - [EmailSender](./core/src/main/java/salarychecker/core/EmailSender.java) :point_right: Sending e-maul to employer.
            - [EncryptDecrypt](./core/src/main/java/salarychecker/core/EncryptDecrypt.java) :point_right: Encryption and decryption of passwords.
            - [Errors](./core/src/main/java/salarychecker/core/Errors.java) :point_right: Errors being displayed for different invalid user-inputs.
            - [IUserObserver](./core/src/main/java/salarychecker/coreIUserObserver.java) 
            - [SalaryCSVReader.java](./core/src/main/java/salarychecker/core/SalaryCSVReader.java) :point_right: Reades sales information from csv-file.
            - [Sale.java](./core/src/main/java/salarychecker/core/Sale.java) :point_right: Creates a new sale, get and set methods for all sale attributes.
            - [UserSale](./core/src/main/java/salarychecker/core/UserSale.java) :point_right: Creates a new sales period for a given user.
            - [UserValidation](./core/src/main/java/salarychecker/core/UserValidation.java) :point_right: Validates user attributes according to predefined parameters.
            - [Accounts](./core/src/main/java/salarychecker/core/Accounts.java) :point_right: Adds users to a list called Accounts. Methods for retrieving users and updating password.
        - [salarychecker/json](./core/src/main/java/salarychecker/json/)
            - Serializers: [UserSerializers](./core/src/main/java/salarychecker/json/UserSerializer.java) , [AccountsSerializer](./core/src/main/java/salarychecker/json/AccountsSerializer.java) , [AdminUserSerializer](./core/src/main/java/salarychecker/json/AdminUserSerializer.java) and [UserSaleSerializer](./core/src/main/java/salarychecker/json/UserSaleSerializer.java) :point_right: These classes serializes the objects to JSON-nodes. This is needed to write a JSON file.
            - Deserializers: [UserDeserializer](./core/src/main/java/salarychecker/json/UserDeserializer.java) ,  [AccountsDeserializer](./core/src/main/java/salarychecker/json/AccountsDeserializer.java) , [AdminUserDeserializer](./core/src/main/java/salarychecker/json/AdminUserDeserializer.java) and [UserSaleDeserializer](./core/src/main/java/salarychecker/json/UserSaleDeserializer.java) :point_right: These classes deserializes the JSON nodes to Java objects.
            - [SalaryCheckerModule](./core/src/main/java/salarychecker/json/SalaryCheckerModule.java)
            - [SalaryCheckerPersistence](./core/src/main/java/salarychecker/json/SalaryCheckerPersistence.java) :point_right: Class with methods for reading, writing and loading classes. 
        - [tests](./core/src/test)
                - [AccountsTest](./core/src/test/java/salarychecker/core/AccountsTest.java) :point_right: Test for Accounts.java
                - [AdminUserTest](./core/src/test/java/salarychecker/core/AdminUserTest.java) :point_right: Test for AdminUser.Java
                - [CalculationTest](./core/src/test/java/salarychecker/core/CalculationTest.java) :point_right: Test for Calculation.java
                - [EncryptDecryptTest](./core/src/test/java/salarychecker/core/EncryptDecryptTest.java) :point_right: Test for EncryptDecrypt.java. 
                - [SalaryCSVReaderTest](./core/src/test/java/salarychecker/core/SalaryCSVReaderTest.java) :point_right: Test for CalaryCSVReader.java.
                - [SaleTest](./core/src/test/java/salarychecker/core/SaleTest.java) :point_right: Test for Sale.java
                - [UserSaleTest](./core/src/test/java/salarychecker/core/UserSaleTest.java) :point_right: Test for UserSale.Java
                - [UserTest](./core/src/test/java/salarychecker/core/UserTest.java) :point_right: Test for User.java
                - [UserValidationTest](./core/src/test/java/salarychecker/core/UserValidationTest.java) :point_right: Test for UserValidation.java
    - [retserver](.restserver/src/main/java/salarychecker/restserver/)
        - [RestServerApplication](.restserver/src/main/java/salarychecker/restserver/RestServerApplication.java) :point_right: RestServer Application Class
        - [SalaryCheckerController](.restserver/src/main/java/salarychecker/restserver/SalaryCheckerController.java) :point_right: Restserver Application Cntroller.
        - [SalaryCheckerService](.restserver/src/main/java/salarychecker/restserver/SalaryCheckerService.java) :point_right: Service class for Rest Server.
                
    - [ui](./ui)
        - [salarychecker/ui](./ui/src/main/java/salarychecker/ui)
            - [SalaryCheckerApp](./ui/src/main/java/salarychecker/ui/SalaryCheckerApp.java) :point_right: App class.
            - [AbstractController](./ui/src/main/java/salarychecker/ui/AbstractController.java) :point_right: Abstract controller for application.
            - [AdminStartPageController] (./ui/src/main/java/salarychecker/ui/AdminSTartPAgeController.java) :point_right: Controller for Admin start scene.
            - [AdminUserOverviewController] (./ui/src/main/java/salarychecker/ui/AdminUserOverviewController.java) :point_right: Controller for the admins overview of users. 
            - [CreateUserController](./ui/src/main/java/salarychecker/ui/CreateUserController.java) :point_right: Controller for admins ability to create users.
            - [LoginController](./ui/src/main/java/salarychecker/ui/LoginController.java) :point_right: Controller for the login scene.
            
            - [ProfileController](./ui/src/main/java/salarychecker/ui/ProfileController.java) :point_right: Controller for users profile scene.
            - [HomepageController](./ui/src/main/java/salarychecker/ui/HomepageController.java) :point_right: Controller for the user start scene.
            - [SettingsController](./ui/src/main/java/salarychecker/ui/SettingsController.java) :point_right: Controller for the users settings scene.
            - [SalariesController](./ui/src/main/java/salarychecker/ui/SalariesController.java) :point_right: Controller for the users overview of previous salaries.
            - [SalaryCalculationController](./ui/src/main/java/salarychecker/ui/SalaryCalculationController) :point_right: Controller for users functionality to calculate salary.
        - [resources](./ui/src/main/resources)
            - [Views](./ui/src/main/resources/views)
                - [LogIn.fxml](./ui/src/main/resources/views/LogIn.fxml) :point_right: FXML-file for login page.
                - [HomePage.fxml](./ui/src/main/resources/views/HomePage.fxml) :point_right: FXML-file for homepage.
                - [AdminStartPage.fxml](./ui/src/main/resources/views/AdminStartPage.fxml) :point_right: FXML-file for admin start scene.
                - [CreateUser.fxml](./ui/src/main/resources/views/CreateUser.fxml) :point_right: FXML-file for admin creating users
                - [AdminUserOverview.fxml](./ui/src/main/resources/views/AdminUserOverview.fxml) :point_right: FXML-file for user overview for admin.
                - [Profile.fxml](./ui/src/main/resources/views/Profile.fxml) :point_right: FXML-file for users profile.
                - [Salaries.fxml](./ui/src/main/resources/views/Salaries.fxml) :point_right: FXML-file for users overview of previous salaries.
                - [SalaryCalculation.fxml](./ui/src/main/resources/views/SalaryCalculation.fxml) :point_right: FXML-file for users ability to calculate salaray.
                - [Settings.fxml](./ui/src/main/resources/views/Settings.fxml) :point_right: FXML-file for settings page.
            - [Styles](./ui/src/main/resources/styles)
                - [AdminStartStylesheet](./ui/src/main/resources/styles/AdminSTartStylesheet.css)
                - [UserOverviewStylesheet](./ui/src/main/resources/styles/UserOverviewStylesheet.css)
                - [CreateUserStylesheet](./ui/src/main/resources/styles/CreateUserStylesheet.css)
                - [BackgroundStylesheet](./ui/src/main/resources/styles/BackgroundStylesheet.css)
                - [LogInStylesheet](./ui/src/main/resources/styles/LogInStylesheet.css)
                - [ProfileStylesheet](./ui/src/main/resources/styles/ProfileStylesheet.css)
                - [SalariesStylesheet](./ui/src/main/resources/styles/SalariesStylesheet.css)
                - [CalculationStylesheet](./ui/src/main/resources/styles/CalculationStylesheet.css)
                - [SettingsStylesheet](./ui/src/main/resources/styles/SettingsStylesheet.css)

        - [tests](./ui/src/test)
            - [AdminStartPageControllerTest](./ui/src/test/AdminStartPageControllerTest.java) :point_right: Test for AdminStartPageController.java
            - [AdminUserOverviewControllerTest](./ui/src/test/AdminUserOverviewControllerTest.java) :point_right: Test for AdminUserOverviewController.
            - [CreateUserControllerTest](./ui/src/test/CreateUserControllerTest.java) :point_right: Test for CreateUserController.
            - [HomePageControllerTest](./ui/src/test/HomePageControllerTest.java) :point_right: Test for HomePageController.java
            - [LogInControllerTest](./ui/src/test/LogInControllerTest.java) :point_right: Test for LogInController.java
            - [ProfileControllerTest](./ui/src/test/ProfileControllerTest.java) :point_right: Test for ProfileController.java
            - [SalariesControllerTest](./ui/src/test/SalariesControllerTest.java) :point_right: Test for SalariesController.java.
            - [SalaryCalculationControllerTest](./ui/src/test/SalaryCalculationControllerTest.java) :point_right: Test for SalaryCalculationController.
            - [SettingsControllerTest](./ui/src/test/SettingsControllerTEst.java) :point_right: Test for SettingsController.java.
                    




