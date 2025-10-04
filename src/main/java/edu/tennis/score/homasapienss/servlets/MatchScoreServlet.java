package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.services.EndedMatchService;
import edu.tennis.score.homasapienss.services.MatchScoreService;
import edu.tennis.score.homasapienss.services.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchesService ongoingMatchesService = OngoingMatchesService.getInstance();
    private final MatchScoreService matchScoreService = new MatchScoreService();
    private final EndedMatchService endedMatchService = new EndedMatchService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        var uuid = UUID.fromString(req.getParameter("uuid"));
        var currentMatch = ongoingMatchesService.getMatch(uuid).orElseThrow(() -> new RuntimeException("Матч не найден"));

        req.setAttribute("uuid", uuid);
        req.setAttribute("match", currentMatch);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var uuid = UUID.fromString(req.getParameter("uuid"));
        int whichPlayerScored = Integer.parseInt(req.getParameter("player"));

        var match = ongoingMatchesService.getMatch(uuid).orElseThrow(() -> new RuntimeException("Матч не найден"));
        MatchDTO calculatedMatch = matchScoreService.calculate(match, whichPlayerScored);

        if (calculatedMatch.getWinner() != null) {
            ongoingMatchesService.removeMatch(uuid);
            endedMatchService.saveMatch(calculatedMatch);
            resp.sendRedirect(req.getContextPath() + "/matches");
            return;
        }

        //resp.sendRedirect(req.getContextPath() + "/match-score?uuid=" + uuid);


        req.setAttribute("uuid", uuid);
        req.setAttribute("match", match);
        req.getRequestDispatcher("match-score.jsp").forward(req,resp);
    }
}
