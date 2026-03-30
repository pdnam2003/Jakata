<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>500 - Internal Server Error</title>
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
        .error-details {
            background-color: #f5f5f5;
            padding: 1rem;
            border-radius: 4px;
            max-width: 600px;
            margin: 1rem 0;
            text-align: left;
            color: #333;
            max-height: 200px;
            overflow-y: auto;
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
            <div class="error-code">500</div>
            <div class="error-message">Internal Server Error</div>
            <div class="error-description">
                An unexpected error occurred while processing your request.
            </div>
            <% if (exception != null) { %>
            <div class="error-details">
                <strong>Error Details:</strong>
                <pre><%= exception.getMessage() %></pre>
            </div>
            <% } %>
            <a href="index.jsp" class="btn">Go to Home</a>
        </div>

        <footer>
            <p>&copy; 2024 Event Management System. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
