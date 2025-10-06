package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.exceptions.ApplicationException;
import edu.tennis.score.homasapienss.services.GeneratedMatchService;
import edu.tennis.score.homasapienss.services.OngoingMatchesService;
import edu.tennis.score.homasapienss.utils.ValidationUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private final GeneratedMatchService generatedMatchService = new GeneratedMatchService();
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name1 = req.getParameter("playerOne");
        String name2 = req.getParameter("playerTwo");
        lox
        try {
            ValidationUtil.validateNames(name1, name2);
        } catch (ApplicationException e) {
            req.setAttribute("exceptionMessage", e.getExceptionMessage());
            req.getRequestDispatcher("exception.jsp").forward(req, resp);
        }
        MatchDTO generatedMatch = generatedMatchService.generateNewMatch(name1, name2);
        UUID uuid = ongoingMatchesService.addMatch(generatedMatch);
        resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);
    }
}
