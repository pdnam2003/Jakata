<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Teams</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Teams</h2>

<c:if test="${not empty message}">
    <div style="color:green">${message}</div>
</c:if>
<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<p><a href="${pageContext.request.contextPath}/teams?action=new">Add New Team</a></p>

<table border="1" cellpadding="5">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>City</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="t" items="${teams}">
        <tr>
            <td>${t.id}</td>
            <td>${t.name}</td>
            <td>${t.city}</td>
            <td>
                <a href="${pageContext.request.contextPath}/teams?action=edit&id=${t.id}">Edit</a>
                |
                <a href="${pageContext.request.contextPath}/teams?action=delete&id=${t.id}"
                   onclick="return confirm('Delete team ${t.name}?');">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

