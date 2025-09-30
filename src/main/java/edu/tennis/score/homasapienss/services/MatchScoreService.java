package edu.tennis.score.homasapienss.services;

import edu.tennis.score.homasapienss.DTO.MatchDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class MatchScoreService {

    public MatchDTO calculate(MatchDTO match, int playerNumber, UUID uuid) {
        if (match.getWinner() == null) {
            MatchDTO.MatchScore matchScore = match.getScore();
            switch (playerNumber) {
                case 1 -> {
                    var scorePlayer1 = matchScore.getScorePlayer1();
                    calculateMatch(scorePlayer1, matchScore.getScorePlayer2());
                    if (scorePlayer1.getSets() == 2) {
                        match.setWinner(match.getPlayer1());
                    }
                }
                case 2 -> {
                    var scorePlayer2 = matchScore.getScorePlayer2();
                    calculateMatch(scorePlayer2, matchScore.getScorePlayer1());
                    if (scorePlayer2.getSets() == 2) {
                        match.setWinner(match.getPlayer2());
                    }
                }
            }
        }
        return match;
    }

    private void calculateMatch(MatchDTO.PlayerScore playerScore, MatchDTO.PlayerScore opponentScore) {
        var pointsPlayer = playerScore.getPoints();

        if (pointsPlayer == 0) {
            playerScore.setPoints((byte) 15);
        } else if (pointsPlayer == 15) {
            playerScore.setPoints((byte) 30);
        } else if (pointsPlayer == 30) {
            playerScore.setPoints((byte) 40);
        } else if (pointsPlayer == 40) {
            if (opponentScore.getPoints() == pointsPlayer) {
                calculateAdvantageGame(playerScore, opponentScore);
            } else {
                resetPoints(playerScore, opponentScore);
                calculateGames(playerScore, opponentScore);
            }
        }
    }

    private void calculateAdvantageGame(MatchDTO.PlayerScore playerScore, MatchDTO.PlayerScore opponentScore) {
        if (playerScore.isHasAdvantage()) {
            resetPoints(playerScore, opponentScore);
            playerScore.setHasAdvantage(false);
            calculateGames(playerScore, opponentScore);
        } else if (opponentScore.isHasAdvantage()) {
            opponentScore.setHasAdvantage(false);
        } else {
            playerScore.setHasAdvantage(true);
        } // больше меньше
    }

    private void resetPoints(MatchDTO.PlayerScore playerScore, MatchDTO.PlayerScore opponentScore) {
        playerScore.setPoints((byte) 0);
        opponentScore.setPoints((byte) 0);
    }

    private void calculateGames(MatchDTO.PlayerScore playerScore, MatchDTO.PlayerScore opponentScore) {
        var gamesPlayer = playerScore.getGames();
        if (gamesPlayer < 6) {
            playerScore.setGames((byte) (gamesPlayer + 1));
        } else {
            if (opponentScore.getGames() == gamesPlayer) {
                //start TIE BREAK
            } else {
                playerScore.setGames((byte) 0);
                opponentScore.setGames((byte) 0);
                calculateSets(playerScore);
            }
        }
    }

    private void calculateSets(MatchDTO.PlayerScore playerScore) {
        var setsPlayer = playerScore.getSets();
        if (setsPlayer <= 1) {
            playerScore.setSets((byte) (setsPlayer + 1));
        }
    }
}
