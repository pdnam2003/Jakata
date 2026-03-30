<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book List - Book Management System</title>
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
        <h2>📚 Book List</h2>

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
            <a href="InsertBookServlet" class="btn-primary">➕ Add New Book</a>
        </div>

        <c:choose>
            <c:when test="${not empty books && books.size() > 0}">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Author</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${books}" var="book">
                            <tr>
                                <td>${book.id}</td>
                                <td>${book.title}</td>
                                <td>${book.author}</td>
                                <td>${book.category.name}</td>
                                <td>$${book.price}</td>
                                <td>
                                    <a href="DeleteBookServlet?id=${book.id}" 
                                       class="btn-delete"
                                       onclick="return confirm('Do you really want to delete this book?');">
                                        🗑️ Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <p class="no-data">No books found. <a href="InsertBookServlet">Add new book</a></p>
            </c:otherwise>
        </c:choose>
    </div>

    <footer>
        <p>&copy; 2024 Book Management System. All rights reserved.</p>
    </footer>
</body>
</html>
