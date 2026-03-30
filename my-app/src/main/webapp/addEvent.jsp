<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Event</title>
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
            <h2>Add New Event</h2>

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

            <form action="AddEventServlet" method="POST" class="form-container">
                <div class="form-group">
                    <label for="name">Event Name:</label>
                    <input type="text" id="name" name="name" required 
                           minlength="5" placeholder="Enter event name (min 5 characters)">
                </div>

                <div class="form-group">
                    <label for="date">Date:</label>
                    <input type="date" id="date" name="date" required>
                </div>

                <div class="form-group">
                    <label for="venue">Venue:</label>
                    <input type="text" id="venue" name="venue" required 
                           placeholder="Enter venue name">
                </div>

                <div class="form-group">
                    <label for="seatsAvailable">Seats Available:</label>
                    <input type="number" id="seatsAvailable" name="seatsAvailable" required 
                           min="1" placeholder="Enter number of seats">
                </div>

                <div class="form-buttons">
                    <button type="submit" class="btn-primary">Add Event</button>
                    <a href="ViewEventsServlet" class="btn-secondary">Cancel</a>
                </div>
            </form>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
