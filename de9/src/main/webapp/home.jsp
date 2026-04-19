<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Kiểm tra session trực tiếp trên JSP hoặc dùng Filter
    if(session.getAttribute("acc") == null) {
        response.sendRedirect("login.jsp");
    }
%>
<html>
<body>
    <h1>HOME PAGE</h1>
    <h3>Hello, ${sessionScope.acc.username}</h3>
    <a href="LogoutServlet">Log out</a>
</body>
</html>