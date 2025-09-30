package edu.tennis.score.homasapienss.mappers;

import edu.tennis.score.homasapienss.DTO.PlayerDTO;
import edu.tennis.score.homasapienss.entities.Player;

public class PlayerMapper {
    public static PlayerDTO toDTO(Player player) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName(player.getName());
        return playerDTO;
    }

    public static Player toEntity(PlayerDTO playerDTO) {
        Player player = new Player();
        player.setName(playerDTO.getName());
        return player;
    }
}
