# Documentation for release 2
### Release 2 introduction:
For release 2 the group intended to present a UI with close to full functionality to operate locally. For release 2 we have mainly focused on implementing all clases and functionality, as well as testing implemented classes.
As the groupmembers differ in programming-experience, pair-programming has been a particularly effective concept. Having a more experienced group member help out less experienced members has been crucial to our prograss. 

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
