package it.uniroma3.expensetracker.filters;

import it.uniroma3.expensetracker.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Authentication filter to validate JWT tokens in incoming requests.
 * This filter checks for the presence and validity of a JWT token in the Authorization header.
 * If the token is valid, it extracts user details from the token and sets them in the request attributes.
 * If the token is invalid or missing, it returns a 403 Forbidden response.
 */
public class AuthFilter extends GenericFilterBean {

    /**
     * The doFilter method processes the incoming request and response to validate the JWT token.
     *
     * @param servletRequest  The incoming request
     * @param servletResponse The outgoing response
     * @param filterChain     The filter chain to pass the request and response to the next filter
     * @throws IOException      If an I/O error occurs during the filter process
     * @throws ServletException If a servlet error occurs during the filter process
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Retrieve the Authorization header from the request
        String authHeader = httpRequest.getHeader("Authorization");
        if (authHeader != null) {
            // Split the Authorization header to extract the token
            String[] authHeaderArr = authHeader.split("Bearer ");
            if (authHeaderArr.length > 1 && authHeaderArr[1] != null) {
                String token = authHeaderArr[1];
                try {
                    // Parse and validate the JWT token
                    Claims claims = Jwts.parser().setSigningKey(Constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    // Set the userId attribute in the request for further processing
                    httpRequest.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));
                } catch (Exception e) {
                    // If token validation fails, send a 403 Forbidden response with an error message
                    httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");
                    return;
                }
            } else {
                // If the token format is incorrect, send a 403 Forbidden response with an error message
                httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
                return;
            }
        } else {
            // If the Authorization header is missing, send a 403 Forbidden response with an error message
            httpResponse.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
            return;
        }
        // Proceed with the next filter in the chain
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
