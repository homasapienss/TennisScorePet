package edu.tennis.score.homasapienss.services;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchesService {
    private static OngoingMatchesService instance;
    private final ConcurrentHashMap<UUID, MatchDTO> ongoingMatches;

    public OngoingMatchesService() {
        this.ongoingMatches = new ConcurrentHashMap<>(10);
    }

    public static OngoingMatchesService getInstance() {
        if(instance == null){
            instance = new OngoingMatchesService();
        }
        return instance;
    }

    public UUID addMatch(MatchDTO match) {
        UUID uuid = generateUUID();
        ongoingMatches.put(uuid, match);
        return uuid;
    }

    public void removeMatch(UUID uuid) {
        ongoingMatches.remove(uuid);
    }

    public Optional<MatchDTO> getMatch(UUID uuid) {
        return Optional.ofNullable(ongoingMatches.get(uuid));
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }
}
