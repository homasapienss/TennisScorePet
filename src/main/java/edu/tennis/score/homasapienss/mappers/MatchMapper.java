package edu.tennis.score.homasapienss.mappers;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.entities.Match;

public class MatchMapper {
    public static Match toEntity(MatchDTO matchDTO) {
        Match match = new Match();
        match.setPlayer1(PlayerMapper.toEntity(matchDTO.getPlayer1()));
        match.setPlayer2(PlayerMapper.toEntity(matchDTO.getPlayer2()));
        match.setWinner(PlayerMapper.toEntity(matchDTO.getWinner()));
        return match;
    }
    public static MatchDTO toDTO(Match match){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setPlayer1(PlayerMapper.toDTO(match.getPlayer1()));
        matchDTO.setPlayer2(PlayerMapper.toDTO(match.getPlayer2()));
        matchDTO.setWinner(PlayerMapper.toDTO(match.getWinner()));
        return matchDTO;
    }
}
