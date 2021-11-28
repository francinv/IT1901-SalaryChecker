# RestServer
>The group chose to implement the Springboot-framework in order to set up the rest-server. Although other alternatives were considered, the group ultimately decided on springboot. Some of Springboots features that led to this desition are: Springboot makes it quick and easy to create stand alone applications, it has Tomcat, Jetty and Undertow embeded so no WAR-file is required, and minimal code generation and no requirement for XML configuration. Ultimately those were some of the conveniences that led the group to using Spring boot instead of creating a rest server manually. The Rest Server module consists of the following classes: 

- RestServerApplication.java, which contains the start-method for the server application.
- RestServerController.java, which makes it possible for the server to listen for HTTP-requests and process them accordingly.
- RestServerService.java, which is used by the controller in order to handle User-objects requested by the client.

Additionally Spring boot has robust serialization and deserialization, of Plain Old Java Objects (POJOs) using Jackson under the hood.

## Supported requests: 

### Request to get accounts

> GET /salarychecker
> 
> Content-Type: application/json
>
> Host: localhost:8080

By using this request, the response will be all the accounts as json.

### Request to get user by e-mail

> GET /salarychecker/user?email=example@mail.com
>
> Content-Type: application/json
>
> Host: localhost:8080

By using this request, the response will be the user with example@mail.com as their email. If noone uses this email, two errors, status409 and User not found will be the response. 

### Request to get users by employer email

> GET /salarychecker/users?employerEmail=example@employer.com
>
> Content-Type: application/json
>
> Host: localhost:8080

By using this request, the response will be all the users who share the employer with email example@employer.com.

### Request to log in

> POST /salarychecker/login?email=example@mail.com&&password=Example123!
>
> Content-Type: application/json
>
> Host: localhost:8080

By using this request, the response will be the user with example@mail.com as their email and Example123! as their password. If noone uses this email, two errors, status code 404 and User not found will be the response. 

### Request to register new set of accounts:
> POST /salarychecker
>
> Content-Type: application/json
>
> Host: localhost:8080
>
> {
>
>       "accounts" : []  
>  
> }

By using this request one will register new set of accounts, the response will be 200 OK if registration was successfull

### Request to create user

> POST /salarychecker/create-user
>
> Content-Type: application/json
>
> Host: localhost:8080
>
> {
>
>       "firstname" : "John",
>
>       "lastname" : "Doe",
>
>       "email" : " "email@example.com",
>
>       "password" : "Password123!",
>
>       "socialNumber" : 31120099999,
>
>       "employeeNumber" : 55555,
>
>       "employerEmail" : "example@employer.com", 
>
>       "taxCount" : 22,
>
>       "hourRate" : 200,
>
> }
>

By using this request, the response will be 200 OK if creation was succesfull. Else status code 409 and User already exists will be the response. 

### Request to create admin user:

> POST /salarychecker/create-user/admin
>
> Content-Type: application/json
>
> Host: localhost:8080
>
> {
>
>       "firstname" : "John",
>
>       "lastname" : "Doe",
>
>       "email" : " "email@example.com",
>
>       "password" : "Password123!",
>
> }
>

By using this request, the response will be 200 OK if creation was succesfull. Else status code 409 and User already exists will be the response. 

### Request to calculate salevalue:

> PUT /salarychecker/user/calculate-sale?email=example@email.com
>
> Content-Type: application/json
>
> Host: localhost:8080
>
> {
>
>       "calculated" : 10000.00,
>
>       "salesperiod" : "Jaunar 2021",
>
>       "hours" : 50,
>
>       "mobileamount" : 10,
>
>       "paid" : 8000
>
> }
>
By using this request the response will be 200 OK if calculation was successfull.

### Request to update user: 
> PUT /salarychecker/user/update-profile?index=1
> Content-Type: application/json
> Host: localhost:8080
> 
>{
>
>       "firstname" : "John",
>
>       "lastname" : "Doe",
>
>       "email" : " "email@example.com",
>
>       "password" : "Password123!",
>
>       "socialNumber" : 31120099999,
>
>       "employeeNumber" : 55555,
>
>       "employerEmail" : "example@employer.com", 
>
>       "taxCount" : 22,
>
>       "hourRate" : 200,
>
> }
>
By using this request the response will be 200 OK if user was updated successfully.

### Request to upload file: 
> POST /salarychecker/uploadFile
>
> Content-Type: application/json
>
> Host: localhost:8080
>
> Body: 
>
> salesreport.csv   
>
By using this request the respons will be 200 OK if file is uploaded successfully. 

### Rquest to get user sale: 
> GET /salarychecker/user/get-user-sale?salesperiod=januar2021&&email=example@mail.com
>
> Content-Type: application/json
>
> Host: localhost:8080
> 
By using this request, the respose will be a UserSale object as json. 

### Request for deleting all accounts:

> DELETE /salarychecker/
>
> Content-Type: application/json
>
> Host: localhost:8080

By using this request the response will be 200 OK if accounts were deleted successfully. 

