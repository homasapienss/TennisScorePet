package edu.tennis.score.homasapienss.servlets;

import edu.tennis.score.homasapienss.DTO.MatchDefinition.MatchDTO;
import edu.tennis.score.homasapienss.DTO.PaginationDTO;
import edu.tennis.score.homasapienss.services.EndedMatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private final EndedMatchService endedMatchService = new EndedMatchService();
    private final int PAGE_SIZE = 5;
    @Override protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pageNumber;
        String filterByName;
        PaginationDTO<MatchDTO> paginationResult;
        Integer page;

        if ((pageNumber = req.getParameter("page")) != null ){
            page = Integer.valueOf(pageNumber);
        } else page = 1;

        if ((filterByName = req.getParameter("filter_by_name"))!=null && !filterByName.isBlank()) {
            paginationResult = endedMatchService.getByNamePagination(filterByName, page, PAGE_SIZE);
        }else paginationResult = endedMatchService.getAllPagination(page, PAGE_SIZE);

        req.setAttribute("currentPage", paginationResult.getCurrentPage());
        req.setAttribute("totalPages", paginationResult.getTotalPages());
        req.setAttribute("finishedMatches", paginationResult.getPaginationList());
        req.setAttribute("filterByName", filterByName);

        req.getRequestDispatcher("matches.jsp").forward(req,resp);
    }
}
