package org.example.ecommerce.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.service.JWTService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final Logger logger = LoggerFactory.getLogger(JWTRequestFilter.class);
    private final UserRepository userRepository;

    @Autowired
    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("x-auth-token");
        if (header != null && header.startsWith("Bearer ")) {
            String jwtToken = header.substring(7);
            try {
                String username = jwtService.getUsername(jwtToken);
                Optional<User> opUser = userRepository.findByUsername(username);
                if(opUser.isPresent()) {
                    User user = opUser.get();
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            } catch (JWTDecodeException e) {
                logger.error("JWTRequestFilter => doFilterInternal => Error: {}", e.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }

}
