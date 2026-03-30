package com.example.servlet;

import com.example.dao.EventDAO;
import com.example.entity.Event;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewEventsServlet")
public class ViewEventsServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error retrieving events: " + e.getMessage());
            request.getRequestDispatcher("viewEvents.jsp").forward(request, response);
        }
    }
}
