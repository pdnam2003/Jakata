<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Manager</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Student Manager</h2>

<c:if test="${not empty message}">
    <div style="color:green">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<p><a href="${pageContext.request.contextPath}/students?action=new">Add Student</a> | <a href="${pageContext.request.contextPath}/">Index</a></p>

<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>Rollnumber</th>
        <th>Name</th>
        <th>Email</th>
        <th>Age</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="s" items="${students}">
        <tr>
            <td>${s.rollnumber}</td>
            <td>${s.name}</td>
            <td>${s.email}</td>
            <td>${s.age}</td>
            <td><a href="${pageContext.request.contextPath}/students?action=delete&roll=${s.rollnumber}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>

