package salarychecker.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Contains the starting method for the server application.
 * Configures Springs ObjectMapper for JSON to parse classes from the core-module.
 */
@SpringBootApplication
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

}
