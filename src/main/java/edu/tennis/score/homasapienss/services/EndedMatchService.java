package edu.tennis.score.homasapienss.services;

import edu.tennis.score.homasapienss.DAO.MatchDAO;
import edu.tennis.score.homasapienss.DAO.PlayerDAO;
import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.DTO.PaginationDTO;
import edu.tennis.score.homasapienss.entities.Match;
import edu.tennis.score.homasapienss.entities.Player;
import edu.tennis.score.homasapienss.mappers.MatchMapper;

import java.util.List;

public class EndedMatchService {
    private final MatchDAO matchDAO;
    private final PlayerDAO playerDAO;

    public EndedMatchService() {
        this.matchDAO = new MatchDAO();
        this.playerDAO = new PlayerDAO();
    }

    public void saveMatch(MatchDTO matchDTO) {
        Match entity = MatchMapper.toEntity(matchDTO);

        String firstName = entity.getPlayer1().getName();
        Player playerOne = playerDAO.findByName(firstName)
                .orElseGet(() -> {
                    Player p = new Player();
                    p.setName(firstName);
                    return playerDAO.create(p);
                });

        String secondName = entity.getPlayer2().getName();
        Player playerTwo = playerDAO.findByName(entity.getPlayer2().getName())
                .orElseGet(() -> {
                    Player p = new Player();
                    p.setName(secondName);
                    return playerDAO.create(p);
                });

        entity.setPlayer1(playerOne);
        entity.setPlayer2(playerTwo);

        if (entity.getWinner().getName().equals(firstName)){
            entity.setWinner(playerOne);
        } else entity.setWinner(playerTwo);

        matchDAO.create(entity);
    }

    private List<MatchDTO> getFinishedMatchesDTO (Integer page, Integer pageSize) {
        return matchDAO.getMatchesWithPagination(page, pageSize)
                .stream()
                .map(MatchMapper::toDTO)
                .toList();
    }
    private List<MatchDTO> getFinishedMatchesByNameDTO (String name, Integer page, Integer pageSize) {
        return matchDAO.getByPlayerNameWithPagination(name, page, pageSize)
                .stream()
                .map(MatchMapper::toDTO)
                .toList();
    }

    public PaginationDTO<MatchDTO> getAllPagination (Integer page, Integer pageSize) {
        PaginationDTO<MatchDTO> paginationResult = new PaginationDTO<>();
        Integer totalCount = matchDAO.countFinishedMatches();
        Integer totalPages = (int) Math.ceil((double) totalCount / pageSize);

        paginationResult.setPaginationList(getFinishedMatchesDTO(page, pageSize));
        paginationResult.setCurrentPage(page);
        paginationResult.setTotalPages(totalPages);
        return paginationResult;
    }
    public PaginationDTO<MatchDTO> getByNamePagination (String name, Integer page, Integer pageSize) {
        PaginationDTO<MatchDTO> paginationResult = new PaginationDTO<>();
        Integer totalCount = matchDAO.countByPlayerName(name);
        Integer totalPages = (int) Math.ceil((double) totalCount / pageSize);

        paginationResult.setPaginationList(getFinishedMatchesByNameDTO(name, page, pageSize));
        paginationResult.setCurrentPage(page);
        paginationResult.setTotalPages(totalPages);
        return paginationResult;
    }

}
