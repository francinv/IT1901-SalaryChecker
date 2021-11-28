# Documentation for release 3: Final release
## Release 3 introduction:
>For release 3 the group intends to present the final and "shippable" product SalaryChecker. During release 3 we have focused on setting up an API and server connection. Very little functionality has been added in terms of user-abilities and functionality, as this was the groups main focus for release 2. In addition to setting up an API, the group has further developed the user interface and tried to optimize it according to principles of user interface design. In this regard the group has paid special attention to the six principles developed by Don Norman.

### Work habits and task management:
>Before the start of sprint 3 the group had a group discussion which concluded with agreement that a slight change of task management was needed. During release 2 writing classes and cooresponding javadoc and test-classes were all listed as seperate tasks or issues. As a consequence, a groupmember would sometimes have to write tests for someone elses code. At times this impaired work flow, and resulted in a rather tedious than effective development process. Consequently the group chose to assign issues in larger categories. Subsequently writing code and corresponding javadoc and test-code was all assigned to the same person. In return this also further prioritized writing effective tests and javadoc, as these are no longer issues that to a greater extent can be procrastinated and written of until right before release.
>
>On the other side, general task and issue distribution has remained mostly the same. The group has still been very fond of the concept of pair-programming. We have, as long as it has been doable and worthwile, tried to assign issues or groups of issues to pairs of members. For the more complex tasks we have ensured that a more experienced group member has been the assignee, or has at least been able to help the assignee as much as nescessary. 

### Methods for quality ensurance of code: 
>As in release 2 "Jacoco" is stil being used, and the goal of testing at least 90% of all code is maintained. However, compared to release 2 the group has put a greater emphasis on "SpotBugs". The group has also implemented CheckStyle for release 3. This has been done to ensure conventional codewriting and more readable classes. Although "CheckStyle" is a great tool, the group has chosen to ignore some of the CheckStyle-errors as it conforms to higher prioritized conventions:
- Interfaces conventionally start with a capital I.
- All final variables are written in capital letters, and seperated by underscore in case of compound variables.
>For the above mentioned cases, checkstyle gives a warning for two subsequent capital letters.
>When all code was written, and the project was in its final stages, the group chose do do a project overhaul. This was done to ensure all criteria was met, code was written conventionally and potential errors made in release 1 and 2 were eliminated.


## Additions of classes and files for release 3:
>The optimization of the user interface has lead the group to split parts of UI-classes and files into more managable sizes. The UI-package now consists of one main, parent controller and multiple sub-controllers, one for each main group of functionality. The fxml-file for the main stage has also been split up accordingly. One parent stage is used as a border, with navigation buttons and menu, and the scene-changes occur in the center of the application as the user navigates around. The group decided to use CSS for a smoother and better looking UI. This was done with one CSS-file for each scene for smoother editing and perhaps more understandable code. The group opted to access UI-components through fx:id instead of component class names. This desition was made for time-purposes, as accessing through class name was not working at first. The group felt it was more time effective to copy paste code where possible. 
>
>Additionally, the group has added classes and files that are needed for setting up a RESTful application.  

### List of classes and files added in release 3:

#### UI:
##### Controller classes:
    - AbstractController.java 
    - AdminStartPageController.java
    - AdminUserOverviewController.java
    - CreateUserController.java
    - HomepageController.java
    - LoginController.java
    - ProfileController.java
    - SalariesController.java
    - SalaryCalculationController.java
    - SettingController.java

##### FXML Files:
    - AdminStartPage.fxml
    - AdminUserOverview.fxml
    - CreateUser.fxml
    - HomePage.fxml
    - Profile.fxml
    - Salaries.fxml
    - SalaryCalculation.fxml
    - Settings.fxml
##### Stylesheets:
    - AdminStartStylesheet
    - UserOverviewStylesheet
    - CreateUserStylesheet
    - BackgroundStylesheet
    - LogInStylesheet
    - ProfileStylesheet
    - SalariesStylesheet
    - CalculationStylesheet
    - SettingsStylesheet

#### RestServer:
    - RestServerApplication.java
    - SalaryCheckerController.Java
    - SalaryCheckerService.java


## Userstory for release 3: 
    "An employer wishes to check if he has made any errors regarding paychecks. He loggs into the client-application, and gets an overview of users. He checks every indiviual user and checks for differences in expected and actual payout."

## Eventual further development:
> After sprint 3 the group feels like the app satisfies its main purpose. A User is able to calculate his salary, and check if he was paid the correct amount. Althoogh the group feels like it has created a usefull and problem-solving application, there are still elements of functionality that could have been added. 

> First and foremost, SalaryChecker is currently only compatible with one specific company. This is because the calculation-process takes company-specific parameters such as product-names and bonuses. This is something the group would change in further development. Ideally an admin would be able to set company-specific calculation-methods which would make the application more universal and for all businesses operating with commision-based salaries. 

> Furthermore there are non-implemented elements of functionality that could have been usefull in the SalaryChecker application. 
1) A user should be able to notify an admin if he encountes a difference in paid and calculated salary. 
2) A user should be able to se his monthly working-hours in a table view.
3) An admin should be able to access the users table-view of previous salaries. 

> The group thinks that the above-mentioned additions would make the application more complete. However, we did not have the time nescescarry to implement them. 

## Afterthoughts and discussion in hindsight of final release:
> After every larger project, including this one, thoughts of evaluation arise. What went well? What could have been done better? Did any changes along the way have a positive or negative impact? The group has chosen to include this segment in an attempt to convey a better understanding of our work, to learn from this experience, and possibly give tips to potential readers and users of this program. 

#### Using git-lab and group communication: 
>For most groupmembers this was their first major encounter with git-lab. In spite of only a slight familiarity, the implementation of gitlab and its functionality went very well. Every task and or changing of code was assigned as a seperate issue. For the most part groupmembers tried not assigning issues to themselves, and rather have someone else assign them to them. In return this further enhanced communication and cooperation between members. With over 600 commits in total the project was continually updated and groupmembers were constantly up to date. Although some flukes (later removed manually) were made, the group will call work with gitlab a great success.

>Before sprint 1 the group had an initial meeting where weekly meeting times and a group contract were manifested. This laid the foundation for communication and working times. The group has used multiple channels of communication including Microsoft Teams and Facebook. Teams was used mostly for remote group meetings, remote pair-programming and sharing of external files. Most other communication was either done in person or over Facebook. The group also regards the communication throughout the projact as a success.   

#### Work habits and task management: 
> As mentioned above, work habits have been great for the most part. Pair programming and the somewhat tactical pairing ensured that no groupmeber faced great challanges by themselves. As a consequence most major tasks were completed in an orderly and timely fashion. Having the group contract as a backbone, working-hours were also overheld for the most part. Whenever group-members might were slacking due to extracurricular activities it was never a problem to give them a nudge, and to refer to the group contract in case of dissagreement. As a consequence work habits were also good for greater parts of the project. 

>Task management was something the group discussed beforehand, but made some wrong estimations about. During sprint 1 this was not much of an issue, as it consisted mostly of project initiation and only a small amount of code was written. Therefore problems surrounding task management did not see daylight until towards the end of sprint 2. At this time the group noticed that writing tests for other members code can be more challanging than expected. In addition the groupmembers who were assigned this task faced a heap-up of work towards the end of the sprint, as it was hard to write test-code for classes that yet had to be made. As a consequence the group changed its approach. Writing a class and correponding tests was from this point and forward assigned to the same person, and issues were not regarded as complete before sufficient tests and javadoc were in place. Furthermore, not prioritizing the implementation of SpotBugs and CheckStyle until the final sprint prooved to be a mistake. This is something the group could have changed earlier, but other things seemed more important at the time. In hindsight it is much more enjoyable to constantly check for CheckStyle errors and and make slight changes to ones code as work progresses rather than doing it all at the end. The result was a tedious and boring bit of work at the beginning of sprint 3. Just like the problem regarding test-code, the solution was to check ones style as work progressed. 

#### Implemented methods for quality ensurance of code:
>"CheckStyle", "Jacoco" and "SpotBugs" all prooved to be great tools ones they were properly implemented. The group feels like it has produced clear and readable code. CheckStyle has provided the group with a common ground for codingstyle. This and the addition of Javadoc hopefully makes the code easier and more enjoyable to read. Jacoco was used from the start, and provided the group with guidance during test-writing. For all three releases the group tried to have a test-coverage of at least 90% striving towards 100%. Although issues arose towards the end of sprint 2, at least the group did not face the same or an even greater issue at final release. SpotBugs prooved to be a usefull tool as well. Although the group faced few actual coding-errors SpotBugs helped clear up problems at times where the code seemed not to work. Conclusively, ignoring the work-habit problems, the group is overall happy with the implementation of these three extentions.

#### Documentation and evaluation:
> Every release contains a mandatory "README.md" file. Theese files contain a brief introduction, userstories for each release, and quick summary of what work had been done. Since this was mandatory for every release, work progress has been seemingly well documented. Although the group is happy with its documentation, we are in agreement that after-sprint-evaluation could have consisted more of "what went wrong" rather than "what was done". Furthermore such evaluations could have been done more frequently as well.

### Conclusive remarks:
> After the projects completion the group has gained a great deal of experience. For some of us it was our first time participating in a larger development project, while others further developed their team-management and facilitating skills. New concepts and techniques, which were not covered in previous courses, were introduced and have given us new knowledge and skills. All in all the project has been a pleasant experience, and we proudly present the SalaryChecker application.  