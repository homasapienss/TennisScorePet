package edu.tennis.score.homasapienss.DTO.MatchDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MatchScore {
    private final PlayerScore scorePlayer1;
    private final PlayerScore scorePlayer2;
    @Setter
    private boolean isTieBreak;

    public MatchScore() {
        this.scorePlayer1 = new PlayerScore();
        this.scorePlayer2 = new PlayerScore();
        this.isTieBreak = false;
    }

    public MatchScore(PlayerScore scorePlayer1, PlayerScore scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
        this.isTieBreak = false;
    }
}
