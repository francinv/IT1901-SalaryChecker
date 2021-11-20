# UI
The group has attempted to build a user interface which is clear and easy to use. The group has tried to implement conventions for UI elements. The attempt to achievce this included a header with application name and current scene title, the main functionality in the center of the application, and a toggleable navigation-menu to the left. 

In order to build the user interface the group has used JavaFX to generate fxml-files for all scenes. The user interface was then styled additionally using Cascading Style Sheets. 

Controller classes are used to process user inputs and perform the wanted functionality. The group opted to have one controller for each UI-scene. The group found this convenient as it made coding easier. Errors and bugs are easier to detect, and subsequent editing is done smoothly . The group also thinks that this will make the UI-model easier to understand for external readers. 

### The UI module consists of the following classes and files: 
#### Controller classes:
- AbstractController
- AdminStartPAgeController
- AdminUserController.java
- CreateUserController.java
- HomepageController.java
- LoginController.java
- ProfileController.java
- SalariesController.java
- SalaryCalculationController.java
- SalaryCheckerApp.java
- SettingsController.java

#### FXML files:
- AdminStartPage.fxml
- AdminUserOverview.fxml
- CreateUser.fxml
- HomePage.fxml
- Profile.fxml
- LogIn.fxml
- Salaries.fxml
- SalaryCalculation.fxml
- Settings.fxml

#### CSS files:
- AdminStartStylesheet.css
- UserOverviewStylesheet.css
- CreateUserStylesheet.css
- BackgroundStylesheet.css
- LogInStylesheet.css
- ProfileStylesheet.css
- SalariesStylesheet.css
- CalculationStylesheet.css
- SettingsStylesheet.css
