package org.example.ecommerce.websockets;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import io.micrometer.common.lang.NonNull;

@Component
public class SocketHandler extends TextWebSocketHandler {
    
    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) throws Exception {
        System.out.println("Client connected: " + session.getId());
    }

    
}
