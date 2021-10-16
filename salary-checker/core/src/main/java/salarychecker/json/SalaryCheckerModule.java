package salarychecker.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import salarychecker.core.*;

/**
 * A Jackson module for configuring JSON serialization of User instances.
 */
public class SalaryCheckerModule extends SimpleModule {

    private static final String NAME = "UserModule";

    /**
     * Initializes this SalaryCheckerModule with appropriate serializers and deserializers.
     */
    public SalaryCheckerModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(User.class, new UserSerializer());
        addSerializer(AdminUser.class, new AdminUserSerializer());
        addSerializer(Accounts.class, new AccountsSerializer());
        addDeserializer(User.class, new UserDeserializer());
        addDeserializer(AdminUser.class, new AdminUserDeserializer());
        addDeserializer(Accounts.class, new AccountsDeserializer());
    }
    
}
