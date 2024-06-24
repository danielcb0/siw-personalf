package it.uniroma3.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Global configuration for Cross-Origin Resource Sharing (CORS).
 * This configuration allows the frontend application to communicate with the backend.
 */
@Configuration
public class GlobalCorsConfig {

    /**
     * Configures and returns a {@link CorsFilter} bean.
     *
     * The CORS filter is set up to allow cross-origin requests from the frontend
     * application running on `http://localhost:3000`. This is necessary for
     * enabling the frontend to interact with the backend API.
     *
     * The filter allows:
     * - Credentials to be included in requests.
     * - Any headers to be included in requests.
     * - Any HTTP methods (GET, POST, PUT, DELETE, etc.) to be used.
     *
     * @return a configured {@link CorsFilter} bean.
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        // Allow credentials to be included in requests (e.g., cookies, authorization headers).
        config.setAllowCredentials(true);

        // Allow requests from the frontend running on localhost:3000.
        config.addAllowedOrigin("http://localhost:3000");

        // Allow all headers to be included in requests.
        config.addAllowedHeader("*");

        // Allow all HTTP methods (GET, POST, PUT, DELETE, etc.).
        config.addAllowedMethod("*");

        // Register the configuration for all paths (/**).
        source.registerCorsConfiguration("/**", config);

        // Return a new CorsFilter with the configured source.
        return new CorsFilter(source);
    }
}
