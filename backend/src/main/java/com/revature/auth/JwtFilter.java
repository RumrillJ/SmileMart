package com.revature.auth;

import com.revature.models.User;
import com.revature.services.JwtService;
import com.revature.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public JwtFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7);
            username = jwtService.extractUsername(jwt);
        }

        //logback instead
        System.out.println(username);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Ensure user exists in database
            Optional<User> user = userService.getUserByUsername(username);

            //if (jwtService.validateToken(jwt) &&) { // if you wanted to check expiration etc
            if (user.isPresent()) {

                // Sets the principal to user, or whatever object you want
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.get(), null, null);

                // i dont know what this does. probably unnecessary
                // probably related to implementing the UserDetails interface
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // sets SecurityContext, which has principal of the user retrieved from DB
                // which can be retrieved from elsewhere in the app by calling
                // SecurityContextHolder.getContext().getAuthentication().getPrincipal()
                SecurityContextHolder.getContext().setAuthentication(auth);

            }

        }

        filterChain.doFilter(request, response);
    }
}
