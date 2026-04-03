<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category List</title>
  
</head>
<body>

    <h2>Category List</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listC}" var="c">
                <tr>
                    <td>C010${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.description}</td>
                    <td><a href="product?cid=${c.id}">Show products</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>