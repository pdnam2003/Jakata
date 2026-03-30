<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Attendees</title>
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
            <h2>View Attendees</h2>

            <c:if test="${not empty error}">
                <div class="error-message">
                    ${error}
                </div>
            </c:if>

            <div class="actions">
                <a href="AddAttendeeServlet" class="btn-primary">Add New Attendee</a>
            </div>

            <c:choose>
                <c:when test="${not empty events && events.size() > 0}">
                    <form method="GET" action="ViewAttendeesServlet" class="filter-form">
                        <label for="eventId">Select Event:</label>
                        <select id="eventId" name="eventId" onchange="this.form.submit()">
                            <option value="">-- Select an Event --</option>
                            <c:forEach items="${events}" var="event">
                                <option value="${event.id}" 
                                    <c:if test="${selectedEventId == event.id}">selected</c:if>>
                                    ${event.name} (${event.date})
                                </option>
                            </c:forEach>
                        </select>
                    </form>

                    <c:choose>
                        <c:when test="${selectedEventId != null}">
                            <h3>Attendees for: ${selectedEvent.name}</h3>
                            
                            <c:if test="${not empty message}">
                                <div class="info-message">
                                    ${message}
                                </div>
                            </c:if>

                            <c:choose>
                                <c:when test="${not empty attendees && attendees.size() > 0}">
                                    <table class="data-table">
                                        <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Name</th>
                                                <th>Email</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${attendees}" var="attendee">
                                                <tr>
                                                    <td>${attendee.id}</td>
                                                    <td>${attendee.name}</td>
                                                    <td>${attendee.email}</td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <p class="no-data">No attendees found for this event.</p>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <p class="no-data">Please select an event to view attendees.</p>
                        </c:otherwise>
                    </c:choose>
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
