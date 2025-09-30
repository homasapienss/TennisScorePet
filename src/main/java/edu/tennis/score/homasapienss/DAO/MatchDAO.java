package edu.tennis.score.homasapienss.DAO;

import edu.tennis.score.homasapienss.entities.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchDAO extends AbstractDAO<Match>{
    public MatchDAO() {
        super(Match.class);
    }
    public List<Match> getMatchesWithPagination(int page, int pageSize) {
        try (Session session = getSession()) {
            return session.createQuery("FROM Match m ORDER BY m.id DESC", Match.class)
                    .setFirstResult((page - 1) * pageSize)  // смещение
                    .setMaxResults(pageSize)               // сколько записей
                    .list();
        }
    }
    public Integer countFinishedMatches() {
        try (Session session = getSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class)
                    .getSingleResult()
                    .intValue();
        }
    }

    public List<Match> getByPlayerNameWithPagination(String namePlayer, int page, int pageSize) {
        try (Session session = getSession()) {
            return session.createQuery("""
                            FROM Match WHERE player1.name = :namePlayer OR player2.name = :namePlayer""", Match.class)
                    .setParameter("namePlayer", namePlayer)
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        }
    }
    public Integer countByPlayerName(String playerName) {
        try (Session session = getSession()) {
            return session.createQuery("""
                SELECT COUNT(m) FROM Match m
                WHERE m.player1.name = :name OR m.player2.name = :name
                """, Long.class)
                    .setParameter("name", playerName)
                    .uniqueResult()
                    .intValue();
        }
    }
}
