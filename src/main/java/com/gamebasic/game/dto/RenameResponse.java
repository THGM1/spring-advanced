package com.gamebasic.game.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class RenameResponse {

    @NotNull
    private final Long gameId;
    @NotNull
    @Size(min=2, max=12)
    private final String playerName;

    public RenameResponse(Long gameId, String playerName) {
        this.gameId = gameId;
        this.playerName = playerName;
    }
}
