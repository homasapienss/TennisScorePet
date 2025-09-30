package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.DTO.MatchDTO;
import edu.tennis.score.homasapienss.services.GeneratedMatchService;
import edu.tennis.score.homasapienss.services.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    GeneratedMatchService generatedMatchService = new GeneratedMatchService();
    OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name1 = req.getParameter("playerOne");
        String name2 = req.getParameter("playerTwo");
        MatchDTO generatedMatch = generatedMatchService.generateNewMatch(name1, name2);
        UUID uuid = ongoingMatchesService.addMatch(generatedMatch);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
