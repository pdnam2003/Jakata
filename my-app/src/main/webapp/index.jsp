<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Event Management System</title>
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
            <h2>Welcome to Event Management System</h2>
            <p>Manage your events and attendees efficiently.</p>
            <div class="features">
                <div class="feature-box">
                    <h3>Add Events</h3>
                    <p>Create new events with details like name, date, venue, and available seats.</p>
                    <a href="AddEventServlet" class="btn">Add Event</a>
                </div>
                <div class="feature-box">
                    <h3>View Events</h3>
                    <p>View all events and manage them.</p>
                    <a href="ViewEventsServlet" class="btn">View Events</a>
                </div>
                <div class="feature-box">
                    <h3>Manage Attendees</h3>
                    <p>Add attendees to events and view their information.</p>
                    <a href="AddAttendeeServlet" class="btn">Manage Attendees</a>
                </div>
            </div>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
