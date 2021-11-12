package salarychecker.ui.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.Properties;

import javafx.fxml.FXML;
import salarychecker.ui.LocalSalaryCheckerAccess;
import salarychecker.ui.RemoteSalaryCheckerAccess;

public class SalaryCheckerAppController {

    @FXML private LoginController loginController;

    private SalaryCheckerConfig config;

    /**
     * Initializes the SalaryCheckerAccess by checking salarychecker.properties. 
     * If the key for remote access is true, the app wil run with RemoteSalaryCheckerAccess, 
     * otherwise LocalSalaryCheckerAccess.
     */
    @FXML
    void initialize() {
        this.config = new SalaryCheckerConfig();

        if (config.getProperty("remoteAccess").equals("true")) {

            loginController.setDataAccess(
                    new RemoteSalaryCheckerAccess(
                            URI.create(config.getProperty("serverUri"))
                    ));

            System.out.println("Using remote endpoint @ " + config.getProperty("serverUri")); 

        } else {
            loginController.setDataAccess(new LocalSalaryCheckerAccess());
        }
    }
    
    /**
     * Configuration class for the app. Decides wheter to run main or tests
     */
    public class SalaryCheckerConfig {

        private Properties properties;

        public SalaryCheckerConfig() {
            this.properties = new Properties();

            try (InputStream inputStream = 
                    getClass().getResourceAsStream("salarychecker.properties")) {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new IllegalStateException("Could not load salarychecker.properties");
            }
        }

        /**
         * By providing a key, this property will return the valye from 
         * salarychecker.properties.
         *
         * @param key the key
         * @return the value of the key
         */
        public String getProperty(String key) {
            return properties.getProperty(key);
        }
    }
}
