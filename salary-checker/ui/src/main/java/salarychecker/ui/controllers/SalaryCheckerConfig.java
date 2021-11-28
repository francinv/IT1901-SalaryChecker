package salarychecker.ui.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import salarychecker.ui.SalaryCheckerApp;

/**
 * Configuration class for the app. Decides wheter to run main or tests
 */
public class SalaryCheckerConfig {

  private Properties properties;
  
  /**
   * Configures properties.
   */
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
