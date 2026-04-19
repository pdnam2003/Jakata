<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User Login</title>
    <style>
        .login-container { width: 350px; margin: 100px auto; border: 1px solid #000; padding: 20px; font-family: Arial; }
        .error { color: red; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="login-container">
        <h2 style="text-align: center; border-bottom: 1px solid #000; padding-bottom: 10px;">User Login</h2>
        
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>

        <form action="login" method="post">
            <p>
                Username: <br>
                <input type="text" name="username" required style="width: 100%;">
            </p>
            <p>
                Password: <br>
                <input type="password" name="password" required style="width: 100%;">
            </p>
            <div style="text-align: center;">
                <button type="submit" style="padding: 5px 20px;">Login</button>
            </div>
        </form>
        <p style="text-align: center; font-size: 0.8em;">
            Don't have an account? <a href="register">Register</a>
        </p>
    </div>
</body>
</html>