package it.uniroma3.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Security configuration for the Expense Tracker application.
 * This configuration sets up CORS, disables CSRF protection for simplicity,
 * and allows all endpoints to be accessed without authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Configures the HTTP security settings.
     *
     * This method sets up the security configurations for HTTP requests.
     * It enables CORS with a custom configuration source, disables CSRF protection,
     * and permits access to all endpoints.
     *
     * @param http the {@link HttpSecurity} to modify.
     * @throws Exception if an error occurs while configuring the HTTP security.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .csrf().disable()  // Disable CSRF protection for simplicity
                .authorizeRequests()
                .antMatchers("/**").permitAll(); // Allow access to all endpoints
    }

    /**
     * Configures and returns a {@link CorsConfigurationSource} bean.
     *
     * The CORS configuration allows requests from the frontend application
     * running on `http://localhost:3000` and permits all headers and methods.
     *
     * @return a configured {@link CorsConfigurationSource} bean.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
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
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Return the configured source.
        return source;
    }
}
