package com.example.web;

import com.example.entity.Team;
import com.example.service.TeamBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Controller servlet that forwards to JSPs.
 */
@WebServlet("/teams")
public class TeamServlet extends HttpServlet {

    @EJB
    private TeamBeanRemote teamBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("new".equals(action)) {
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
            return;
        } else if ("edit".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    Team t = teamBean.getTeamById(id);
                    req.setAttribute("team", t);
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "Invalid id");
                }
            }
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
            return;
        } else if ("delete".equals(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                try {
                    teamBean.deleteTeam(Integer.parseInt(idStr));
                    req.setAttribute("message", "Deleted successfully");
                } catch (Exception e) {
                    req.setAttribute("error", "Error deleting team: " + e.getMessage());
                }
            }
        }
        List<Team> list = teamBean.getAllTeams();
        req.setAttribute("teams", list);
        req.getRequestDispatcher("/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String idStr = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("city");

        try {
            if (idStr == null || idStr.isEmpty()) {
                teamBean.addTeam(name, city);
            } else {
                teamBean.updateTeam(Integer.parseInt(idStr), name, city);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Operation failed: " + e.getMessage());
            req.getRequestDispatcher("/form.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/teams");
    }
}

