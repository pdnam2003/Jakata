# Book Management System - Complete Implementation Guide

## Overview
This document describes the Book Management System web application implementation, featuring user authentication, authorization, and CRUD operations for managing a book collection.

---

## Part I: Features Implementation (16 Points Total)

### 1 & 2. Login Functionality (4 Points) ✓

#### Overview
User authentication system with session management.

#### Components

**JSP File**: [login.jsp](login.jsp)
- Form with POST method
- Inputs: username, password
- Error message display
- Clean, intuitive UI with gradient background

**Servlet**: `LoginServlet.java`
- GET: Display login form (redirect to home if already logged in)
- POST: Handle authentication
  - Receive username/password parameters
  - Query database for matching user
  - Success: Create session with attributes:
    - `"user"`: username
    - `"userId"`: user ID
    - `"fullName"`: full name
  - Redirect to home.jsp
  - Failure: Display error message "Please enter correct username and password to login"
  - Forward back to login.jsp

**Database**: `users` table
- id (Primary Key)
- username (UNIQUE)
- password
- email
- full_name
- created_at

**Sample Credentials**:
```
username: admin
password: password
```

---

### 3. Authorization / Authentication Filter (1 Point) ✓

#### Overview
Automatic protection of all pages requiring authentication.

#### Implementation

**Filter Class**: `AuthenticationFilter.java`
- Intercepts all requests (`/*`)
- Exempts:
  - Static resources (CSS, JS, images)
  - Login page (`login.jsp`)
  - Login servlet (`LoginServlet`)
- Checks `session.getAttribute("user")`
- If user is null:
  - Redirects to login.jsp
  - Prevents access to protected resources
- If user exists:
  - Allows request to proceed
  - Sets up for page to use session data

#### Protected Resources
- home.jsp
- list.jsp (book list)
- insert.jsp (add book)
- ListBookServlet
- InsertBookServlet
- DeleteBookServlet

---

### 4. Homepage (2 Points) ✓

#### Overview
User-friendly dashboard with quick navigation.

#### Components

**JSP File**: [home.jsp](home.jsp)
- Displays welcome message with user's full name
- Shows 3 feature cards:
  1. **View All Books** - Link to ListBookServlet
  2. **Add New Book** - Link to InsertBookServlet
  3. **Manage Books** - Link to ListBookServlet
- Logout link - calls LogoutServlet
- Professional design with gradient styling

**Logout Functionality**: `LogoutServlet.java`
- GET/POST request handler
- Calls `session.invalidate()`
- Redirects to login.jsp
- Destroys all session data

#### Features
- User name display
- Quick navigation
- Responsive layout
- Gradient UI design

---

### 5. List All Books (5 Points) ✓

#### 5a. Display Book List (Required: 4 points, Bonus: 1 point)

**Servlet**: `ListBookServlet.java`
- GET request handler
- Queries database for all books
- Uses BookDAO.getAllBooks()
- JOIN with categories table:
  ```sql
  SELECT b.*, c.name FROM books b JOIN categories c ON b.category_id = c.id
  ```
- Passes book list to list.jsp

**JSP File**: [list.jsp](list.jsp)
- Uses JSTL `<c:forEach>` loop
- Displays books in HTML table format
- Table columns:
  - ID
  - Title
  - Author
  - Category (from joined categories table)
  - Price
  - Actions (Delete button)
- Success/Error message display
- "No books found" message if empty
- "Add New Book" link

**Features**:
- Professional table styling
- Hover effects
- Responsive design
- Icon usage (📚, ➕)

#### 5b. Delete Book (Required: 1 point)

**Delete Implementation**:
```html
<a href="DeleteBookServlet?id=${book.id}" 
   class="btn-delete"
   onclick="return confirm('Do you really want to delete this book?');">
   🗑️ Delete
</a>
```

**Servlet**: `DeleteBookServlet.java`
- GET request handler
- Receives book ID parameter
- Calls BookDAO.deleteBook(id)
- Handles exceptions gracefully
- Redirects back to ListBookServlet
- Success/error message display

**JavaScript Confirmation**:
- `onclick="return confirm('Do you really want to delete this book?');"`
- Prevents accidental deletion
- User must confirm action
- Returns false to cancel

---

### 6. Insert New Book (4 Points) ✓

#### 6a. Load Categories from Database

**Servlet**: `InsertBookServlet.java` (GET method)
- Queries CategoryDAO.getAllCategories()
- Passes list to insert.jsp

**Database**: `categories` table
- id (Primary Key)
- name (UNIQUE)
- description

**Sample Categories**:
- Fiction
- Non-Fiction
- Science
- History
- Self-Help
- Travel
- Biography

#### 6b. JSP Form with Dynamic Category Dropdown

**JSP File**: [insert.jsp](insert.jsp)
- Form with POST method
- Form fields:
  - Title (required, minlength=3)
  - Author (optional)
  - Category (dropdown, required)
  - Price (number, required, positive)
  - ISBN (optional)
  - Description (textarea, optional)

**Category Dropdown** (No hard-coding):
```jsp
<select id="categoryId" name="categoryId" required>
    <option value="">-- Select a Category --</option>
    <c:forEach items="${categories}" var="category">
        <option value="${category.id}">${category.name}</option>
    </c:forEach>
</select>
```

#### 6c. Validation

**Client-Side (HTML5)**:
- `required` attribute on mandatory fields
- `type="number"` with `min="0.01"` and `step="0.01"` for price
- `minlength="3"` for title

**Server-Side (Java)**:
- Title validation:
  - Not empty
  - Minimum 3 characters
- Category validation:
  - Must be selected
  - Must exist in database
- Price validation:
  - Not empty
  - Valid decimal number
  - Must be positive (> 0)
- Error collection and display

**Validation Error Messages**:
```
"Title cannot be empty"
"Title must be at least 3 characters"
"Category must be selected"
"Selected category not found"
"Price cannot be empty"
"Price must be a valid number"
"Price must be a positive number"
```

#### 6d. Insert Processing

**Servlet**: `InsertBookServlet.java` (POST method)
- Receives parameters: title, author, categoryId, price, isbn, description
- Validates all inputs
- If errors: Display errors and reload form
- If valid:
  - Create Book entity
  - Populate all fields
  - Call BookDAO.addBook(book)
  - Display success message
  - Redirect to list.jsp OR reload form with success message

---

## Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    full_name VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Categories Table
```sql
CREATE TABLE categories (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### Books Table
```sql
CREATE TABLE books (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    category_id BIGINT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(500),
    isbn VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);
```

---

## File Structure

```
Book Management System
├── Entity Classes (3)
│   ├── User.java
│   ├── Category.java
│   └── Book.java
├── DAO Classes (3)
│   ├── UserDAO.java
│   ├── CategoryDAO.java
│   └── BookDAO.java
├── Filter Classes (1)
│   └── AuthenticationFilter.java
├── Servlet Classes (6)
│   ├── LoginServlet.java
│   ├── LogoutServlet.java
│   ├── ListBookServlet.java
│   ├── DeleteBookServlet.java
│   ├── InsertBookServlet.java
│   └── + previous event management servlets
├── JSP Pages (4)
│   ├── login.jsp
│   ├── home.jsp
│   ├── list.jsp
│   └── insert.jsp
├── Configuration
│   ├── persistence.xml (updated)
│   └── web.xml
└── Database
    └── book_management_setup.sql
```

---

## Implementation Points Breakdown

| Feature | Points | Status |
|---------|--------|--------|
| 1. Login - Giao diện | 1 | ✓ |
| 2. Login - Xử lý | 3 | ✓ |
| 3. Phân quyền/Filter | 1 | ✓ |
| 4. Homepage | 2 | ✓ |
| 5a. List books - Display | 4 | ✓ |
| 5b. Delete book | 1 | ✓ |
| 6. Insert new book | 4 | ✓ |
| **Total** | **16** | **✓ Complete** |

---

## Security Features

✓ Session-based authentication
✓ AuthenticationFilter protection
✓ JavaScript confirmation dialog for deletion
✓ Parameterized queries (prevent SQL injection)
✓ Input validation (client & server-side)
✓ Password stored in database
✓ Session timeout support

---

## Testing Scenarios

### Test 1: Login
1. Navigate to login.jsp
2. Enter: username=admin, password=password
3. Expected: Redirect to home.jsp, session created
4. Try: username=invalid, password=wrong
5. Expected: Error message displayed

### Test 2: Protection
1. Try to access list.jsp without login
2. Expected: Redirect to login.jsp
3. Login first, then access list.jsp
4. Expected: Book list displayed

### Test 3: List Books
1. Login and go to home.jsp
2. Click "View Books"
3. Expected: Book table with all books displayed
4. Check JOIN: Category names displayed (not IDs)

### Test 4: Delete Book
1. In book list, click Delete button
2. Expected: Confirmation dialog appears
3. Click OK
4. Expected: Book removed from database and list
5. Click Cancel
6. Expected: No deletion, list unchanged

### Test 5: Insert Book
1. Click "Add Book" from home or list page
2. Categories dropdown loads
3. Expected: All categories displayed (not hard-coded)
4. Fill form with valid data
5. Click Add
6. Expected: Success message, book added to database
7. Try invalid data (price=-5, title="ab")
8. Expected: Error messages displayed

### Test 6: Logout
1. Click Logout link
2. Expected: Session invalidated, redirect to login.jsp
3. Try to access list.jsp directly
4. Expected: Redirect to login.jsp

---

## Key Implementation Points

1. **No Hard-Coding**: Categories loaded dynamically from database
2. **Confirmation Dialog**: JavaScript confirm() for deletion
3. **Error Handling**: User-friendly error messages
4. **Validation**: Both client-side (HTML5) and server-side (Java)
5. **Join Query**: BookDAO includes category name display
6. **Session Management**: Proper session creation and invalidation
7. **Filter Protection**: All protected pages automatically checked
8. **Responsive Design**: Works on all screen sizes

---

## Database Setup Instructions

1. Execute `book_management_setup.sql`:
   ```bash
   mysql -u root -p < book_management_setup.sql
   ```

2. Verify database:
   ```sql
   USE book_management;
   SHOW TABLES;
   SELECT * FROM users;
   SELECT * FROM categories;
   SELECT * FROM books;
   ```

3. Update persistence.xml with credentials if needed

---

## Usage Instructions

1. Build and deploy project
2. Navigate to http://localhost:8080/event-management/login.jsp
3. Login with demo credentials (admin/password)
4. Explore all features:
   - View books (with category names from JOIN)
   - Add new books
   - Delete books with confirmation
   - Logout

---

**Document Version**: 1.0  
**Status**: Complete - All 16 Points Implemented  
**Date**: 2024
