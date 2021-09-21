package salarychecker.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import salarychecker.core.*;

/**
 * A Jackson module for configuring JSON serialization of User instances.
 */
@SuppressWarnings("serial")
public class SalaryCheckerModule extends SimpleModule {

    private static final String NAME = "UserModule";

    /**
     * Initializes this TodoModule with appropriate serializers and deserializers.
     */
    public SalaryCheckerModule(boolean deepSalaryCheckerModelSerializer) {
        super(NAME, Version.unknownVersion());
        addSerializer(User.class, new UserSerializer());
        //addSerializer(TodoModel.class, new TodoModelSerializer(deepSalaryCheckerModelSerializer));
    }

    public SalaryCheckerModule() {
        this(true);
    }
}
