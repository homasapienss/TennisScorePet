package edu.tennis.score.homasapienss.DTO.MatchDefinition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerScore {
    private Points points;
    private int games;
    private int sets;
    private boolean hasAdvantage;

    public PlayerScore() {
        this.points = Points.ZERO;
    }

    public PlayerScore(Points points, int games, int sets, boolean hasAdvantage) {
        this.points = points;
        this.games = games;
        this.sets = sets;
        this.hasAdvantage = hasAdvantage;
    }
}
