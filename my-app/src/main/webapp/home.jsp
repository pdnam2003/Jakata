<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Book Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        .home-container {
            max-width: 1000px;
            margin: 0 auto;
            padding: 40px 20px;
        }
        .welcome-section {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 40px;
            border-radius: 8px;
            margin-bottom: 40px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
        }
        .welcome-section h1 {
            margin: 0 0 10px 0;
        }
        .welcome-section p {
            margin: 0;
            opacity: 0.9;
        }
        .features-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }
        .feature-card {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s, box-shadow 0.3s;
        }
        .feature-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15);
        }
        .feature-card h3 {
            color: #667eea;
            margin-top: 0;
        }
        .feature-card p {
            color: #666;
            line-height: 1.6;
        }
        .feature-card a {
            display: inline-block;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 10px 20px;
            border-radius: 4px;
            text-decoration: none;
            margin-top: 15px;
            transition: transform 0.2s;
        }
        .feature-card a:hover {
            transform: translateY(-2px);
        }
    </style>
</head>
<body>
    <nav class="navbar">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <h1>📚 Book Management System</h1>
            <div>
                <span style="margin-right: 20px;">Welcome, <strong><%= session.getAttribute("fullName") != null ? session.getAttribute("fullName") : session.getAttribute("user") %></strong></span>
                <a href="LogoutServlet" class="logout-link">Logout</a>
            </div>
        </div>
    </nav>

    <div class="home-container">
        <div class="welcome-section">
            <h1>Welcome to Book Management System</h1>
            <p>Manage your book collection efficiently with a simple and intuitive interface.</p>
        </div>

        <div class="features-grid">
            <div class="feature-card">
                <h3>📖 View All Books</h3>
                <p>Browse through our complete collection of books. See all details including title, author, category, and price.</p>
                <a href="ListBookServlet">View Books</a>
            </div>

            <div class="feature-card">
                <h3>➕ Add New Book</h3>
                <p>Add a new book to the collection. Fill in the details and select a category to organize your books.</p>
                <a href="InsertBookServlet">Add Book</a>
            </div>

            <div class="feature-card">
                <h3>🔧 Manage Books</h3>
                <p>Edit, delete, or update book information. Keep your collection up to date with the latest information.</p>
                <a href="ListBookServlet">Manage</a>
            </div>
        </div>
    </div>

    <footer>
        <p>&copy; 2024 Book Management System. All rights reserved.</p>
    </footer>

    <style>
        .logout-link {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 8px 16px;
            border-radius: 4px;
            text-decoration: none;
            transition: opacity 0.3s;
        }
        .logout-link:hover {
            opacity: 0.9;
        }
    </style>
</body>
</html>
