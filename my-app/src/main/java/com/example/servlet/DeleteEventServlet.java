package com.example.servlet;

import com.example.dao.EventDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteEventServlet")
public class DeleteEventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("eventId");
            if (idStr != null && !idStr.trim().isEmpty()) {
                Long eventId = Long.parseLong(idStr);
                eventDAO.deleteEvent(eventId);
                request.setAttribute("success", "Event deleted successfully!");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error deleting event: " + e.getMessage());
        }
        response.sendRedirect("ViewEventsServlet");
    }
}
