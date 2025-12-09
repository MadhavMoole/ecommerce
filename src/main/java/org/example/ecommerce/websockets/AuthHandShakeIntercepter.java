package org.example.ecommerce.websockets;

import java.util.Map;

import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class AuthHandShakeIntercepter implements HandshakeInterceptor {
    
    private final JWTService jwtService;
    
    @Autowired
    public AuthHandShakeIntercepter(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {

        String token = ((ServletServerHttpRequest) request)
                .getServletRequest()
                .getParameter("token");

        if (token == null || !jwtService.isValid(token)) {
            return false;
        }
        String userId = jwtService.getUsername(token);
        attributes.put("userId", userId);

        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler wsHandler,
            Exception ex) {}
}
