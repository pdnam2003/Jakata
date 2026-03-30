<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Attendee</title>
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
            <h2>Add New Attendee</h2>

            <c:if test="${not empty success}">
                <div class="success-message">
                    ${success}
                </div>
            </c:if>

            <c:if test="${not empty errors}">
                <div class="error-message">
                    <ul>
                        <c:forEach items="${errors}" var="error">
                            <li>${error}</li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <c:if test="${not empty error}">
                <div class="error-message">
                    ${error}
                </div>
            </c:if>

            <c:choose>
                <c:when test="${not empty events && events.size() > 0}">
                    <form action="AddAttendeeServlet" method="POST" class="form-container">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" id="name" name="name" required 
                                   minlength="3" placeholder="Enter attendee name (min 3 characters)">
                        </div>

                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" id="email" name="email" required 
                                   placeholder="Enter email address">
                        </div>

                        <div class="form-group">
                            <label for="eventId">Event:</label>
                            <select id="eventId" name="eventId" required>
                                <option value="">-- Select an Event --</option>
                                <c:forEach items="${events}" var="event">
                                    <option value="${event.id}">${event.name} (${event.date})</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="form-buttons">
                            <button type="submit" class="btn-primary">Add Attendee</button>
                            <a href="ViewAttendeesServlet" class="btn-secondary">Cancel</a>
                        </div>
                    </form>
                </c:when>
                <c:otherwise>
                    <p class="no-data">No events available. Please <a href="AddEventServlet">add an event</a> first.</p>
                </c:otherwise>
            </c:choose>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
