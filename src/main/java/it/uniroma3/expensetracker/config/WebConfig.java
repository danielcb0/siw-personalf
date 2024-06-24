package it.uniroma3.expensetracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration class for the Expense Tracker application.
 * This class implements {@link WebMvcConfigurer} to provide custom configurations
 * for the Spring MVC framework, specifically to set up Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configures CORS mappings to allow cross-origin requests from the frontend.
     *
     * This method adds CORS mappings to allow requests from the frontend application
     * running on `http://localhost:3000`. It permits all standard HTTP methods, allows
     * any headers, and enables credentials to be included in requests.
     *
     * @param registry the {@link CorsRegistry} to add CORS mappings to.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Add CORS mappings for all paths (/**)
        registry.addMapping("/**")
                // Allow requests from the frontend running on localhost:3000
                .allowedOrigins("http://localhost:3000")
                // Allow standard HTTP methods
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Allow all headers to be included in requests
                .allowedHeaders("*")
                // Allow credentials to be included in requests (e.g., cookies, authorization headers)
                .allowCredentials(true);
    }
}
