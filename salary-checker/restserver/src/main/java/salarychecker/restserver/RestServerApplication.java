package salarychecker.restserver;

import com.fasterxml.jackson.databind.Module;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import salarychecker.json.SalaryCheckerPersistence;

/**
 * Contains the starting method for the server application.
 * Configures Springs ObjectMapper for JSON to parse classes from the core-module.
 */
@SpringBootApplication
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

	@Bean
	public Module objectMapperModule() {
		return SalaryCheckerPersistence.createJacksonModule();
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/salarychecker").allowedOrigins("http://localhost:8080");
			}
		};
	}


}
