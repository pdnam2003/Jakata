<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><c:choose><c:when test="${not empty team}">Edit Team</c:when><c:otherwise>Add Team</c:otherwise></c:choose></title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2><c:choose><c:when test="${not empty team}">Edit Team</c:when><c:otherwise>Add Team</c:otherwise></c:choose></h2>

<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/teams">
    <input type="hidden" name="id" value="${team.id}" />
    <label>Name: <input type="text" name="name" value="${team.name}" required maxlength="100"/></label><br/>
    <label>City: <input type="text" name="city" value="${team.city}" maxlength="100"/></label><br/>
    <button type="submit">Save</button>
</form>

<p><a href="${pageContext.request.contextPath}/teams">Back to list</a></p>
</body>
</html>

