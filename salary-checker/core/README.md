# Core
The core module consist of classes that process user input, and perform the applications intended functionality. The classes are made according to one specific need and use for the application. To some external readers the amount of classes may seem excessive. However, the group found this class-division convenient. Potential application-errors will be easy to find and edit. 

The core module also includes tests for functionality-classes. The group has focused on testing all classes, and has tried to achieve a test coverage of at least 90%, using the Jacoco-plugin. 

### The core module consists of the following classes and test classes:
#### Classes:
- AbstractUser.Java :point_right: Abstractifies User and AdminUser-objects in order to store them in the same list. 
- Accounts.java :point_right: Keeps track of all AbstractUser-objects.
- AdminUser.java :point_right: Generates AdminUser-objects.
- Calculation.java :point_right: Calulates the value of a sales-period.
- EncryptDecrypt.java :point_right: Encrypts and decrypts user-passwords.
- Errors.java :point_right: Initialization of constant error-messages.
- UserObserver.java :point_right: Interface for observing user objects.
- SalaryCsvReader.java :point_right: Reads a Csv-file and translates it to processable strings. 
- Sale.java :point_right: Processes information read from SalaryCsvReder.java.
- User.java :point_right: Generates User-objects.
- UserSale.java :point_right: Calculates differences in expected and actual payout for a given salesperiod. 
- UserValidation.java :point_right: Validates User and AdminUser-parameters.

#### Test classes:
- AccountsTest.java :point_right: Test for Accounts.java
- AdminUserTest.java :point_right: Test for AdminUser.java
- CalculationTest.java :point_right: Test for Calculation.java
- EncryptDecryptTest.java :point_right: Test for EncryptDecrypt.java
- SalaryCsvReaderTest.java :point_right: Test for SalaryCsvReader.java
- SaleTest.java :point_right: Test for Sale.java
- UserTest.java :point_right: Test for User.java
- UserSaleTest.java :point_right: Test for UserSale.java
- UserValidationTest.java :point_right: Test for USerValidation.java

### Connections between classes:
Firstly the Errors Enum is simply an Enum where errors are declared. Errors are being displayed in the user interface whenever the user does something illegal. 

Secondly, User.java generates a user object, which can be added to the list of users in Accounts.java. User.java also includes set and get methods for User-object parameters. AdminUser.java generates an AdminUser-object. AdminUser differs from the regular User. An admin User can only access the Admin-scenes in the application. AbstractUser abstractifies User and AdminUser objects by storing the common parameters. This allows us to store them in the same list. This simplifies the coding of the LogIn process. When generating a User or AdminUser-object, UserValidation.java validates all parameters, and throws IllegalArgumentExeptions in case of invalid user input. Accounts.java observes User-objects using the UserObserver-interface.

Furthermore, encrypt decrypt encrypts and decrypts User and admin Passwords. This is done in order to store an encrypted version of the password for securityreasons. 

Lastly, SalaryCsvReader.java allows the application to recieve a Csv-file and store the information in order to generate a sale-object. The sale object is then processed in Calculation.java, and asserted to a user in UserSale.java. Calculation.java processes all sales information from the Csv-file oploaded to SalaryCsvReader.java, and calculates the total worth of a Users salesperiod. UserSale then accesses a given sales-period, and compares it to the user-input given in the user interface. It then calculates the differnece between the amount a user has been paid, and what he is owed. 

Test-classes test functionality of respective classes as written in the "TestClasses" section.  


