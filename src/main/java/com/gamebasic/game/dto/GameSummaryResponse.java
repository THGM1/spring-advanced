package com.gamebasic.game.dto;

import com.gamebasic.game.entity.GamePhase;
import com.gamebasic.game.entity.GameStatus;
import com.gamebasic.game.service.GameService;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class GameSummaryResponse {

    private final Long id;
    private final String playerName;
    private final int currentFloor;
    private final int currentHp;
    private final GamePhase phase;
    private final GameStatus status;
    private final int deckSize;
    @CreatedDate
    private final LocalDateTime createdAt;
    @LastModifiedDate
    private final LocalDateTime updateAt;


    public GameSummaryResponse(Long id, String playerName, int currentFloor, int currentHp, GamePhase phase, GameStatus status, int deckSize, LocalDateTime createdAt, LocalDateTime updateAt) {
        this.id = id;
        this.playerName = playerName;
        this.currentFloor = currentFloor;
        this.currentHp = currentHp;
        this.phase = phase;
        this.status = status;
        this.deckSize = deckSize;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }
}
