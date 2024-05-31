package com.example.computernetworkserver;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashSet;
import java.util.Set;

public class SocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        String response = processMessage(payload);

        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage(response));
            }
        }
    }

    private String processMessage(String message) {
        // 369 게임 로직 구현
        int number;
        try {
            number = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            return "숫자를 입력해주세요.";
        }

        if (contains369(number)) {
            return number + " - 짝!";
        } else {
            return String.valueOf(number);
        }
    }

    private boolean contains369(int number) {
        String numStr = String.valueOf(number);
        return numStr.contains("3") || numStr.contains("6") || numStr.contains("9");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
