<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book - Book Management System</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar">
        <div style="display: flex; justify-content: space-between; align-items: center;">
            <div>
                <h1>📚 Book Management System</h1>
            </div>
            <div style="display: flex; gap: 20px; align-items: center;">
                <a href="home.jsp" class="nav-link">Home</a>
                <a href="ListBookServlet" class="nav-link">Books</a>
                <a href="InsertBookServlet" class="nav-link">Add Book</a>
                <span style="margin-right: 20px;">Welcome, <strong><%= session.getAttribute("fullName") != null ? session.getAttribute("fullName") : session.getAttribute("user") %></strong></span>
                <a href="LogoutServlet" class="logout-link">Logout</a>
            </div>
        </div>
    </nav>

    <div class="content">
        <h2>➕ Add New Book</h2>

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

        <c:choose>
            <c:when test="${not empty categories && categories.size() > 0}">
                <form action="InsertBookServlet" method="POST" class="form-container">
                    <div class="form-group">
                        <label for="title">Book Title:</label>
                        <input type="text" id="title" name="title" required 
                               minlength="3" placeholder="Enter book title (min 3 characters)">
                    </div>

                    <div class="form-group">
                        <label for="author">Author:</label>
                        <input type="text" id="author" name="author" 
                               placeholder="Enter author name">
                    </div>

                    <div class="form-group">
                        <label for="categoryId">Category:</label>
                        <select id="categoryId" name="categoryId" required>
                            <option value="">-- Select a Category --</option>
                            <c:forEach items="${categories}" var="category">
                                <option value="${category.id}">${category.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="price">Price:</label>
                        <input type="number" id="price" name="price" required 
                               min="0.01" step="0.01" placeholder="Enter price">
                    </div>

                    <div class="form-group">
                        <label for="isbn">ISBN:</label>
                        <input type="text" id="isbn" name="isbn" 
                               placeholder="Enter ISBN (optional)">
                    </div>

                    <div class="form-group">
                        <label for="description">Description:</label>
                        <textarea id="description" name="description" rows="4" 
                                  placeholder="Enter book description (optional)"></textarea>
                    </div>

                    <div class="form-buttons">
                        <button type="submit" class="btn-primary">➕ Add Book</button>
                        <a href="ListBookServlet" class="btn-secondary">Cancel</a>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <p class="no-data">No categories available. Please add categories first.</p>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        <p>&copy; 2024 Book Management System. All rights reserved.</p>
    </footer>

    <style>
        textarea {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            padding: 0.75rem;
            border: 1px solid #bdc3c7;
            border-radius: 4px;
            font-size: 1rem;
            width: 100%;
            box-sizing: border-box;
            transition: border-color 0.3s;
        }
        textarea:focus {
            outline: none;
            border-color: #3498db;
            box-shadow: 0 0 5px rgba(52, 152, 219, 0.3);
        }
    </style>
</body>
</html>
