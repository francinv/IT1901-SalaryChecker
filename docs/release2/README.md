# Documentation for release 2
### Release 2 introduction:
For release 2 the group intended to present a UI with close to full functionality to operate locally. For release 2 we have mainly focused on implementing all classes and functionality, as well as testing implemented classes. As a result the user interface is not optimally designed, and rather presened as a prototype to ensure working funcyionality.

#### Working habits and task management:
As the groupmembers differ in programming-experience, pair-programming has been a particularly effective concept. Having a more experienced group member help out less experienced members has been crucial to our progress. The group has maintained a line of communication throughout the project, and response time has been short whenever group members run into issues. The group has also been striving towards having two group meetings per week. This however has not always been possible, nor necessary. Due to our rapid communication and frequent updating, two group meetings per week were slightly excessive. Working in pairs and communicating through Microsoft Teams and Git Lab prooved to be sufficient.   

#### Methods for quality encurance of code:
The group has implemented a strategy of writing tests for all of our code. This is to ensure that every class and method is working in its intended way. In order to keep track of our testing-progression the group has been using "Jacoco". The group has decided to strive for over 90% of code being tested. Whenever Jacoco suggests such a degree of testing is not achieved, the group will add further tests. 

In order to further ensure a smooth user experience after the final release the group has desided to implement the "Spotbugs" maven-plugin. However, for release 2, the group has chosen to focus on spotbugs to a lesser degree. This was done in order to prioritize coding and implementing all wanted functionality for release 2. The group will instead strive towards eliminating all spotbugs before release 3. 

#### Classes added for release 2:
##### Core:
- AbstractUser.java
- AdminUser.java
- Calculation.java
- EmailSender.java
- EncryptDecrypt.java
- Errors.java
- IUserObserver.java
- SalaryCSVReader.java
- Sale.java
- UserSale.java

##### Test:
- AccountsTest.java
- AdminUserTest.java
- CalculationTest.java
- EncryptDecryptTest.java
- SaleTest.java
- UserSaleTest.java
- UserTest.java
- UserValidationTest.java

##### UI:
- AdminController.java
- SettingsController.java

### User Stories for release 2:
#### Story 1:
    "The previously logged in employee wishes to verify that his last paycheck was indeed correct. He opens the "Lønnsutregniger" tab and enters the amount he/she was payed. The employee proceeds to enter her total hours worked and uploads his/her sales report for the previous month.

    The employee notices a difference in amount that has been payed and what he/she is really owed. The employee wants to notify his/her employer via email. The employee navigates to the E-Mail tab, enters what month the complaint is about, and sends an E-Mail to the employer."
#### Story 2:
    "An emplyee feels like he/she is on a positive trend in terms of salary. He/She wishes to compare her last months sales to previous ones. She navigates to the "Tidligere lønninger" tab and examines the overview of her previous paychecks."
#### Story 3: 
    "After realising the positive trend in salary the emplyee notices she will enter a different tax bracket than he/she was previously in. He/She wishes to update his/her profile accordingly. The employee also realizes his/her password has not been changed in a while and wishes to update this as well. The employee opens the settings window where he/she can make the previously mentioned changes."   
#### Story 4:
    "An employer has recently hired someone new. The employer wishes to set up a new SalaryChecker account for the employee. He logs into the SalaryChecker application using his admin-user. He then navigates to the "ny bruker" tab and creates the new user." 