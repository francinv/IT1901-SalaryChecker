package salarychecker.ui.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import salarychecker.ui.LocalSalaryCheckerAccess;
import salarychecker.ui.RemoteSalaryCheckerAccess;
import salarychecker.ui.SalaryCheckerApp;

public class SalaryCheckerAppController extends AbstractController {

    @FXML private Parent includedView;
    @FXML private LoginController loginController; // $includedView;+Controller

    private SalaryCheckerConfig config;

    /**
     * Initializes the SalaryCheckerAccess by checking salarychecker.properties. 
     * If the key for remote access is true, the app wil run with RemoteSalaryCheckerAccess, 
     * otherwise LocalSalaryCheckerAccess.
     * @throws IOException
     */
    @FXML
    void initialize() throws IOException {
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
                    SalaryCheckerApp.class.getResourceAsStream("salarychecker.properties")) {
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

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
