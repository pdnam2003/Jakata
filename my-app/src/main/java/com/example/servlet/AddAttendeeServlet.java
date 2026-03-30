package com.example.servlet;

import com.example.dao.AttendeeDAO;
import com.example.dao.EventDAO;
import com.example.entity.Attendee;
import com.example.entity.Event;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AddAttendeeServlet")
public class AddAttendeeServlet extends HttpServlet {
    private AttendeeDAO attendeeDAO = new AttendeeDAO();
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading events: " + e.getMessage());
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String eventIdStr = request.getParameter("eventId");

        List<String> errors = new ArrayList<>();

        // Validation
        if (name == null || name.trim().isEmpty()) {
            errors.add("Name cannot be empty");
        } else if (name.trim().length() < 3) {
            errors.add("Name must be at least 3 characters");
        }

        if (email == null || email.trim().isEmpty()) {
            errors.add("Email cannot be empty");
        } else if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errors.add("Email must be in a valid email format");
        }

        if (eventIdStr == null || eventIdStr.trim().isEmpty()) {
            errors.add("Event must be selected");
        }

        if (!errors.isEmpty()) {
            try {
                List<Event> events = eventDAO.getAllEvents();
                request.setAttribute("events", events);
            } catch (Exception e) {
                // Ignore
            }
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
            return;
        }

        try {
            Long eventId = Long.parseLong(eventIdStr);
            Event event = eventDAO.getEventById(eventId);

            if (event == null) {
                errors.add("Selected event not found");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
                return;
            }

            Attendee attendee = new Attendee();
            attendee.setName(name.trim());
            attendee.setEmail(email.trim());
            attendee.setEvent(event);

            attendeeDAO.addAttendee(attendee);

            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);
            request.setAttribute("success", "Attendee added successfully!");
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            errors.add("Invalid event ID");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
        } catch (Exception e) {
            errors.add("Error: " + e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("addAttendee.jsp").forward(request, response);
        }
    }
}
