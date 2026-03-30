<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>404 - Page Not Found</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .error-container {
            text-align: center;
            padding: 3rem;
            min-height: 70vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .error-code {
            font-size: 4rem;
            color: #e74c3c;
            font-weight: bold;
            margin-bottom: 1rem;
        }
        .error-message {
            font-size: 1.5rem;
            color: #2c3e50;
            margin-bottom: 1.5rem;
        }
        .error-description {
            color: #666;
            margin-bottom: 2rem;
        }
    </style>
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

        <div class="content error-container">
            <div class="error-code">404</div>
            <div class="error-message">Page Not Found</div>
            <div class="error-description">
                The page you are looking for does not exist or has been moved.
            </div>
            <a href="index.jsp" class="btn">Go to Home</a>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
