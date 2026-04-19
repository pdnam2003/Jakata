<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>User Registration</title></head>
<body>
    <div style="width: 300px; margin: auto; border: 1px solid #000; padding: 20px;">
        <h2 style="text-align: center;">User Registration</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <form action="register" method="post">
            Username: <input type="text" name="username" required style="width: 100%;"><br><br>
            Email: <input type="email" name="email" required style="width: 100%;"><br><br>
            Password: <input type="password" name="password" minlength="6" required style="width: 100%;"><br><br>
            <button type="submit" style="width: 100%;">Register</button>
        </form>
        <p>Already have an account? <a href="login">Login here</a></p>
    </div>
</body>
</html>