<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Product Management</title></head>
<body>
    <h2>Welcome, ${sessionScope.user.username}! | <a href="logout">Logout</a></h2>
    <hr>
    <a href="product-form">Add New Product</a>
    <table border="1" cellpadding="10">
        <tr>
            <th>ID</th><th>Name</th><th>Price</th><th>Quantity</th><th>Actions</th>
        </tr>
        <c:forEach var="p" items="${products}">
            <tr>
                <td>${p.id}</td>
                <td><c:out value="${p.name}"/></td>
                <td>${p.price}</td>
                <td>${p.quantity}</td>
                <td>
                    <a href="delete-product?id=${p.id}" onclick="return confirm('Delete this?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>