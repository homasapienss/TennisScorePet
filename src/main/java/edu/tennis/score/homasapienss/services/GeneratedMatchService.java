package edu.tennis.score.homasapienss.services;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.DTO.PlayerDTO;

public class GeneratedMatchService {

    public MatchDTO generateNewMatch(String name1, String name2) {
        PlayerDTO player1 = createNewPlayer(name1);
        PlayerDTO player2 = createNewPlayer(name2);

        MatchDTO match = new MatchDTO();

        match.setPlayer1(player1);
        match.setPlayer2(player2);

        return match;
    }

    private PlayerDTO createNewPlayer(String name) {
        PlayerDTO p = new PlayerDTO();
        p.setName(name);
        return p;
    }
}
