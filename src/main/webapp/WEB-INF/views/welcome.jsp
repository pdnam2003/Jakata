<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Welcome</title></head>
<body>
    <div style="text-align: center; margin-top: 50px;">
        <h1>Welcome, <span style="color: blue;">${sessionScope.user.username}</span>!</h1>
        <p>You have successfully logged in.</p>
        <hr style="width: 50%;">
        <nav>
            <a href="products">Manage Products</a> | 
            <a href="logout">Logout</a>
        </nav>
    </div>
</body>
</html>