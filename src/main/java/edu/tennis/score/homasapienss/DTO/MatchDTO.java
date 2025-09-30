package edu.tennis.score.homasapienss.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDTO {
    private PlayerDTO player1;
    private PlayerDTO player2;
    private PlayerDTO winner;
    private MatchScore score = new MatchScore();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MatchScore {
        private PlayerScore scorePlayer1 = new PlayerScore();
        private PlayerScore scorePlayer2 = new PlayerScore();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlayerScore {
        private byte points;
        private byte games;
        private byte sets;
        private boolean hasAdvantage;
    }
}
