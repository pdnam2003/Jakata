<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Student Manage</title>
    <meta charset="UTF-8"/>
</head>
<body>
<h2>Student Manage</h2>

<c:if test="${not empty error}">
    <div style="color:red">${error}</div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/students">
    <label>Rollnumber: <input type="text" name="rollnumber" value="${student.rollnumber}" required /></label><br/>
    <label>Name: <input type="text" name="name" value="${student.name}" required /></label><br/>
    <label>Email: <input type="email" name="email" value="${student.email}" /></label><br/>
    <label>Age: <input type="number" name="age" value="${student.age}" /></label><br/>
    <c:if test="${not empty student}">
        <input type="hidden" name="existing" value="true" />
    </c:if>
    <button type="submit">Add</button>
    <button type="reset">Clear</button>
</form>

<p><a href="${pageContext.request.contextPath}/students">List of Student</a> | <a href="${pageContext.request.contextPath}/">Index</a></p>

</body>
</html>

