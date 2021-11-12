package salarychecker.ui;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javafx.fxml.FXML;

public class SalaryCheckerAppController {

    @FXML private LoginController loginController;

    private SalaryCheckerConfig config;

    
    
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
