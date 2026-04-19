<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>List Of Group</h1>
<table border="1">
    <tr>
        <th>ID</th><th>Group Name</th><th>Description</th>
    </tr>
    <c:forEach items="${groups}" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.name}</td>
            <td>${g.description}</td>
        </tr>
    </c:forEach>
</table>
<br>
<button onclick="window.location.href='AddGroup.jsp'">Add Group</button>
<button onclick="window.location.href='list-contacts'">List Contact</button>