<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="dal.GroupDAO, model.Group, java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add a Contact</title>
</head>
<body>
    <h1>Add a Contact</h1>
    
    <%-- Hiển thị thông báo lỗi nếu validation thất bại --%>
    <p style="color:red">${error}</p>

    <form action="add-contact" method="post">
        <table border="0">
            <tr>
                <td>First Name:</td>
                <td><input type="text" name="firstName"></td>
            </tr>
            <tr>
                <td>Last Name:</td>
                <td><input type="text" name="lastName"></td>
            </tr>
            <tr>
                <td>Group:</td>
                <td>
                    <select name="groupId">
                        <%
                            GroupDAO gDao = new GroupDAO();
                            List<Group> groups = gDao.getAllGroups();
                            for(Group g : groups) {
                        %>
                            <option value="<%=g.getId()%>"><%=g.getName()%> - <%=g.getDescription()%></option>
                        <% } %>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Phone Number:</td>
                <td><input type="text" name="phoneNumber"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Add"></td>
                <td></td>
            </tr>
        </table>
    </form>
    
    <br>
    <form action="list-contacts" method="get">
        <input type="submit" value="Return To Contact List">
    </form>
</body>
</html>