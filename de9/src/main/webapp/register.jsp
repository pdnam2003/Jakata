<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Register Page</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>REGISTER</h1>
        <form action="register" method="post">
            Username: <input type="text" name="username"><br><br>
            Password: <input type="password" name="password"><br><br>
            Email: <input type="email" name="email"><br><br>
            Address: <textarea name="address"></textarea><br><br>
            <input type="submit" value="Register">
            <input type="reset" value="Reset">
        </form>
    </div>
</body>
</html>