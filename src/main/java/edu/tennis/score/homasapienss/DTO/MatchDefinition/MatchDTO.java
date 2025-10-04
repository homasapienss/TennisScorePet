package edu.tennis.score.homasapienss.DTO.MatchDefinition;

import edu.tennis.score.homasapienss.DTO.PlayerDTO;
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
}
