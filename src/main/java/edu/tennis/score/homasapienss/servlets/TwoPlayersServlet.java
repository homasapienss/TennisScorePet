package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.DAO.MatchDAO;
import edu.tennis.score.homasapienss.DAO.PlayerDAO;
import edu.tennis.score.homasapienss.entities.Match;
import edu.tennis.score.homasapienss.entities.Player;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/two-players")
public class TwoPlayersServlet extends HttpServlet {
    private PlayerDAO playerDAO = new PlayerDAO();
    private MatchDAO matchDAO = new MatchDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("players", playerDAO.findAll());
        req.setAttribute("matches", matchDAO.findAll());
        req.getRequestDispatcher("two-players.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var firstName = req.getParameter("playerOne");
        var secondName = req.getParameter("playerTwo");

        Match match = new Match();
        Player playerOne = playerDAO.findByName(firstName)
                .orElseGet(() -> {
                    Player p = new Player();
                    p.setName(firstName);
                    return playerDAO.create(p);
                });

        // ищем игрока 2
        Player playerTwo = playerDAO.findByName(secondName)
                .orElseGet(() -> {
                    Player p = new Player();
                    p.setName(secondName);
                    return playerDAO.create(p);
                });

        match.setPlayer1(playerOne);
        match.setPlayer2(playerTwo);

        match.setWinner(playerOne);
        matchDAO.create(match);

        resp.sendRedirect(req.getContextPath() + "/two-players");
    }
}
