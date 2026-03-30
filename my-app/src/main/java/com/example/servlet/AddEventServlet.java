package com.example.servlet;

import com.example.dao.EventDAO;
import com.example.entity.Event;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddEventServlet")
public class AddEventServlet extends HttpServlet {
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("addEvent.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String dateStr = request.getParameter("date");
        String venue = request.getParameter("venue");
        String seatsStr = request.getParameter("seatsAvailable");

        List<String> errors = new ArrayList<>();

        // Validation
        if (name == null || name.trim().isEmpty()) {
            errors.add("Event name cannot be empty");
        } else if (name.trim().length() < 5) {
            errors.add("Event name must have at least 5 characters");
        }

        if (dateStr == null || dateStr.trim().isEmpty()) {
            errors.add("Date cannot be empty");
        }

        if (venue == null || venue.trim().isEmpty()) {
            errors.add("Venue cannot be empty");
        }

        if (seatsStr == null || seatsStr.trim().isEmpty()) {
            errors.add("Seats available cannot be empty");
        } else {
            try {
                int seats = Integer.parseInt(seatsStr);
                if (seats <= 0) {
                    errors.add("Seats available must be a positive number");
                }
            } catch (NumberFormatException e) {
                errors.add("Seats available must be a valid number");
            }
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addEvent.jsp").forward(request, response);
            return;
        }

        try {
            Event event = new Event();
            event.setName(name.trim());
            event.setDate(LocalDate.parse(dateStr));
            event.setVenue(venue.trim());
            event.setSeatsAvailable(Integer.parseInt(seatsStr));

            eventDAO.addEvent(event);
            request.setAttribute("success", "Event added successfully!");
            request.getRequestDispatcher("addEvent.jsp").forward(request, response);
        } catch (Exception e) {
            errors.add("Error: " + e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addEvent.jsp").forward(request, response);
        }
    }
}
