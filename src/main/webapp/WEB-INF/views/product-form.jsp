<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Add New Product</title></head>
<body>
    <div style="width: 400px; margin: auto; border: 1px solid #000; padding: 20px;">
        <h2 style="text-align: center;">Add New Product</h2>
        <form action="add-product" method="post">
            Name: <input type="text" name="name" required style="width: 100%;"><br><br>
            Description: <textarea name="description" style="width: 100%;"></textarea><br><br>
            Price: <input type="number" step="0.01" name="price" min="0" required style="width: 100%;"><br><br>
            Quantity: <input type="number" name="quantity" min="0" required style="width: 100%;"><br><br>
            <button type="submit" style="width: 100%;">Add Product</button>
        </form>
        <br>
        <a href="products">Back to List</a>
    </div>
</body>
</html>