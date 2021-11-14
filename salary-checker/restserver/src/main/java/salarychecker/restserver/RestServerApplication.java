package salarychecker.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import salarychecker.restserver.properties.FileStorageProperties;

/**
 * Contains the starting method for the server application.
 * Configures Springs ObjectMapper for JSON to parse classes from the core-module.
 */
@SpringBootApplication
@EnableConfigurationProperties({
	FileStorageProperties.class
})
public class RestServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServerApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
				.addMapping("/salarychecker/**").allowedOrigins("http://localhost:3000/").allowedMethods("PUT","POST","GET");
			}
		};
	}


}
