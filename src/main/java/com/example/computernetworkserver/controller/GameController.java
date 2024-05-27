package com.example.computernetworkserver.controller;

import com.example.computernetworkserver.dto.GameMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    private static final String TOPIC_DESTINATION = "/topic/game";

    @MessageMapping("/game.send")
    @SendTo(TOPIC_DESTINATION)
    public GameMessage send(GameMessage gameMessage) {
        return gameMessage;
    }

    // 추가적인 메서드로 참가자 관리, 게임 로직 처리 등을 구현할 수 있습니다.
}
