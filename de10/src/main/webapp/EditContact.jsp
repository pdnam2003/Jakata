<h1>Edit a Contact</h1>
<form action="edit" method="post">
    <input type="hidden" name="id" value="${contact.id}">
    ID: <input type="text" value="${contact.id}" disabled><br>
    First Name: <input type="text" name="firstName" value="${contact.firstName}"><br>
    Last Name: <input type="text" name="lastName" value="${contact.lastName}"><br>
    Group ID: <input type="number" name="groupId" value="${contact.groupId}"><br>
    Phone Number: <input type="text" name="phoneNumber" value="${contact.phoneNumber}"><br>
    <input type="submit" value="Edit">
</form>
<a href="list-contacts">Return To Contact List</a>