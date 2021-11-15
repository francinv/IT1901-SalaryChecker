# Json

The Json-module consist of classes that serialize and deseralize java-objects. The Json module is where the applications persistence is located. This is needed in order to transfer information from the local applicatien to the client application. 
The group has created classes for converting Javacode and object-instances to Json format and vice versa. This has been done for all classes that need translation to and from Json-format. 

#### The module consists of the following serializers and deserializers:
    - AccountsDeserializer.java :point_right: Translates Accounts from Json to Javacode
    - AccountsSerializer.java :point_right: Translates Accounts from Javacode to Json
    - AdminUserDeserializer.java :point_right: Translates AdminUser from Json to Javacode
    - AdminUserSerializer.java :point_right: Translates AdminUser from Javacode to Json
    - UserDeserializer.java :point_right: Translates User from Json to Javacode
    - UserSerializer.java :point_right: Translates User from Javacode to Json
    - UserSaleDeserializer.java :point_right: Translates UserSale from Json to Javacode
    - UserSaleSerializer.java :point_right: Translates UserSale from Javacode to Json

#### Persistence: 
    - SalaryCheckerPersistence.java :point_right: Stores information needed for running the application. 
