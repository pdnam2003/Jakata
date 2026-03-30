<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Events</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <div class="container">
        <nav class="navbar">
            <h1>Event Management System</h1>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="AddEventServlet">Add Event</a></li>
                <li><a href="ViewEventsServlet">View Events</a></li>
                <li><a href="AddAttendeeServlet">Add Attendee</a></li>
                <li><a href="ViewAttendeesServlet">View Attendees</a></li>
            </ul>
        </nav>

        <div class="content">
            <h2>Event List</h2>

            <c:if test="${not empty success}">
                <div class="success-message">
                    ${success}
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="error-message">
                    ${error}
                </div>
            </c:if>

            <div class="actions">
                <a href="AddEventServlet" class="btn-primary">Add New Event</a>
            </div>

            <c:choose>
                <c:when test="${not empty events && events.size() > 0}">
                    <table class="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Date</th>
                                <th>Venue</th>
                                <th>Seats Available</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${events}" var="event">
                                <tr>
                                    <td>${event.id}</td>
                                    <td>${event.name}</td>
                                    <td>${event.date}</td>
                                    <td>${event.venue}</td>
                                    <td>${event.seatsAvailable}</td>
                                    <td>
                                        <form action="DeleteEventServlet" method="POST" style="display:inline;">
                                            <input type="hidden" name="eventId" value="${event.id}">
                                            <button type="submit" class="btn-delete" 
                                                    onclick="return confirm('Are you sure you want to delete this event?');">
                                                Delete
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No events found. <a href="AddEventServlet">Add new event</a></p>
                </c:otherwise>
            </c:choose>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
