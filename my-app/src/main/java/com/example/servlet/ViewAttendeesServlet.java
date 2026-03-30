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
import java.util.List;

@WebServlet("/ViewAttendeesServlet")
public class ViewAttendeesServlet extends HttpServlet {
    private AttendeeDAO attendeeDAO = new AttendeeDAO();
    private EventDAO eventDAO = new EventDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String eventIdStr = request.getParameter("eventId");
        
        try {
            List<Event> events = eventDAO.getAllEvents();
            request.setAttribute("events", events);

            if (eventIdStr != null && !eventIdStr.trim().isEmpty()) {
                try {
                    Long eventId = Long.parseLong(eventIdStr);
                    List<Attendee> attendees = attendeeDAO.getAttendeesByEventId(eventId);
                    Event selectedEvent = eventDAO.getEventById(eventId);
                    
                    request.setAttribute("attendees", attendees);
                    request.setAttribute("selectedEvent", selectedEvent);
                    request.setAttribute("selectedEventId", eventId);
                    
                    if (attendees.isEmpty()) {
                        request.setAttribute("message", "No attendees found for this event");
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("error", "Invalid event ID");
                }
            }
            request.getRequestDispatcher("viewAttendees.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error retrieving data: " + e.getMessage());
            request.getRequestDispatcher("viewAttendees.jsp").forward(request, response);
        }
    }
}
