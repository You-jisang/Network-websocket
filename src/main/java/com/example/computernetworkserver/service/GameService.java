package com.example.computernetworkserver.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameService {

    private final Map<String, Boolean> readyStatus = new ConcurrentHashMap<>();

    public void setReady(String userId, boolean isReady) {
        readyStatus.put(userId, isReady);
        checkAllReady();
    }

    private void checkAllReady() {
        if (readyStatus.values().stream().allMatch(b -> b)) {
            // 모든 사용자가 준비되었다면 게임 시작 로직 실행
        }
    }

    // 게임 로직 메서드...
}
