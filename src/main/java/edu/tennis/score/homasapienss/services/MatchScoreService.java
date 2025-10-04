package edu.tennis.score.homasapienss.services;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchScore;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.PlayerScore;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.Points;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class MatchScoreService {

    public MatchDTO calculate(MatchDTO match, int playerNumber) {
        if (match.getWinner() == null) {
            MatchScore matchScore = match.getScore();
            var scorePlayer1 = matchScore.getScorePlayer1();
            var scorePlayer2 = matchScore.getScorePlayer2();

            switch (playerNumber) {
                case 1 -> {
                    if (!matchScore.isTieBreak()) {
                        calculateMatch(scorePlayer1, scorePlayer2);
                    } else matchScore.setTieBreak(calculateTieBreakMatch(scorePlayer1, scorePlayer2));
                    if (scorePlayer1.getSets() == 2) {
                        match.setWinner(match.getPlayer1());
                    }
                }
                case 2 -> {
                    if (!matchScore.isTieBreak()) {
                        calculateMatch(scorePlayer2, scorePlayer1);
                    } else matchScore.setTieBreak(calculateTieBreakMatch(scorePlayer2, scorePlayer1));
                    if (scorePlayer2.getSets() == 2) {
                        match.setWinner(match.getPlayer2());
                    }
                }
            }
            int gamesPlayer1 = scorePlayer1.getGames();
            int gamesPlayer2 = scorePlayer2.getGames();
            if ((gamesPlayer1 == 6) && (gamesPlayer2 == 6)) {
                matchScore.setTieBreak(true);
            }
        }
        return match;
    }

    private boolean calculateTieBreakMatch(PlayerScore playerScore, PlayerScore opponentScore) {
        var pointsPlayer = playerScore.getPoints();

        if (pointsPlayer == Points.ZERO) {
            playerScore.setPoints(Points.ONE);
        } else if (pointsPlayer == Points.ONE) {
            playerScore.setPoints(Points.TWO);
        } else if (pointsPlayer == Points.TWO) {
            playerScore.setPoints(Points.THREE);
        } else if (pointsPlayer == Points.THREE) {
            playerScore.setPoints(Points.FOUR);
        } else if (pointsPlayer == Points.FOUR) {
            playerScore.setPoints(Points.FIVE);
        } else if (pointsPlayer == Points.FIVE) {
            if (opponentScore.getPoints() == Points.SIX) {
                resetPoints(playerScore, opponentScore);
            } else playerScore.setPoints(Points.SIX);
        } else if (pointsPlayer == Points.SIX) {
            resetPoints(playerScore, opponentScore);
            calculateGames(playerScore, opponentScore);
            return false;
        }
        return true;
    }

    private void calculateMatch(PlayerScore playerScore, PlayerScore opponentScore) {

        var pointsPlayer = playerScore.getPoints();

        if (pointsPlayer == Points.ZERO) {
            playerScore.setPoints(Points.FIFTEEN);
        } else if (pointsPlayer == Points.FIFTEEN) {
            playerScore.setPoints(Points.THIRTY);
        } else if (pointsPlayer == Points.THIRTY) {
            playerScore.setPoints(Points.FORTY);
        } else if (pointsPlayer == Points.FORTY) {
            if (opponentScore.getPoints() == pointsPlayer) {
                calculateAdvantageGame(playerScore, opponentScore);
            } else {
                resetPoints(playerScore, opponentScore);
                calculateGames(playerScore, opponentScore);
            }
        }
    }

    private void calculateAdvantageGame(PlayerScore playerScore, PlayerScore opponentScore) {
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

    private void resetPoints(PlayerScore playerScore, PlayerScore opponentScore) {
        playerScore.setPoints(Points.ZERO);
        opponentScore.setPoints(Points.ZERO);
    }

    private void calculateGames(PlayerScore playerScore, PlayerScore opponentScore) {
        var gamesPlayer = playerScore.getGames();
        if (gamesPlayer < 6) {
            playerScore.setGames((gamesPlayer + 1));
        } else {
            resetGames(playerScore, opponentScore);
            calculateSets(playerScore);
        }
    }

    private void resetGames(PlayerScore playerScore, PlayerScore opponentScore) {
        playerScore.setGames(0);
        opponentScore.setGames(0);
    }

    private void calculateSets(PlayerScore playerScore) {
        var setsPlayer = playerScore.getSets();
        if (setsPlayer <= 1) {
            playerScore.setSets(setsPlayer + 1);
        }
    }
}
