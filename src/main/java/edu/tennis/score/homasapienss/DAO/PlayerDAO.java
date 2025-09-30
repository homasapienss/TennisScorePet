package edu.tennis.score.homasapienss.DAO;

import edu.tennis.score.homasapienss.entities.Player;
import org.hibernate.Session;

import java.util.Optional;

public class PlayerDAO extends AbstractDAO<Player> {
    public PlayerDAO() {
        super(Player.class);
    }

    public Optional<Player> findByName(String name) {
        Session session = getSession();
        return session.createQuery(
                "FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .uniqueResultOptional();
    }
}
