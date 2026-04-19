<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>
    <p>There are <b>${total}</b> contacts in the List</p>
    
    <table border="1" style="border-collapse: collapse; width: 80%;">
        <thead>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Group</th>
                <th>Phone Number</th>
                <th>Operations</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${contacts}" var="c">
                <tr>
                    <td>${c.id}</td>
                    <td>${c.firstName}</td>
                    <td>${c.lastName}</td>
                    <td>${c.groupName}</td>
                    <td>${c.phoneNumber}</td>
                    <td>
                        <a href="edit?id=${c.id}">Edit</a> 
                        <a href="delete?id=${c.id}" onclick="return confirm('Are you sure?')">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    
    <br>
    <button onclick="window.location.href='AddContact.jsp'">Add Contact</button>
    <button onclick="window.location.href='list-groups'">List Group</button>
</body>
</html>