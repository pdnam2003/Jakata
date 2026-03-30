# Event Management System - Web Application

## Overview
This is a comprehensive JSP/Servlet web application for managing events and attendees using JPA (Java Persistence API) with MySQL database. The application provides a user-friendly interface for creating, viewing, and managing events and their attendees with full input validation and exception handling.

## Features

### Event Management
- **Add Events**: Create new events with the following details:
  - Event name (minimum 5 characters)
  - Event date
  - Venue
  - Number of available seats (positive number)

- **View Events**: Display all events in a tabular format with columns:
  - ID, Name, Date, Venue, and Seats Available
  - Each event has a delete option

- **Delete Events**: Remove events from the database

### Attendee Management
- **Add Attendees**: Register attendees to events with:
  - Attendee name (minimum 3 characters)
  - Email address (valid email format)
  - Event selection (dropdown)

- **View Attendees**: Display all attendees for a selected event in a table format with:
  - ID, Name, and Email columns
  - Displays a message if no attendees found

## Technical Stack

### Backend
- **Java 11+**
- **Maven** - Build and dependency management
- **JSP/Servlet** - Web layer
- **JPA/Hibernate** - ORM framework
- **MySQL 8.0+** - Database

### Frontend
- **HTML5**
- **CSS3** - Responsive and modern styling
- **JSP** - Server-side templating
- **JSTL** - Tag libraries for JSP

### Validation
- **Client-side**: HTML5 validation
- **Server-side**: 
  - JPA annotations (@NotNull, @NotEmpty, @Size, @Email, @Positive)
  - Hibernate Validator
  - Custom validation in Servlets

## Project Structure

```
event-management/
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── entity/
│   │   │   │   ├── Event.java
│   │   │   │   └── Attendee.java
│   │   │   ├── dao/
│   │   │   │   ├── EventDAO.java
│   │   │   │   └── AttendeeDAO.java
│   │   │   ├── servlet/
│   │   │   │   ├── AddEventServlet.java
│   │   │   │   ├── ViewEventsServlet.java
│   │   │   │   ├── DeleteEventServlet.java
│   │   │   │   ├── AddAttendeeServlet.java
│   │   │   │   └── ViewAttendeesServlet.java
│   │   │   └── util/
│   │   │       └── EntityManagerUtil.java
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   └── webapp/
│   │       ├── index.jsp
│   │       ├── addEvent.jsp
│   │       ├── viewEvents.jsp
│   │       ├── addAttendee.jsp
│   │       ├── viewAttendees.jsp
│   │       └── css/
│   │           └── style.css
│   └── test/
│       └── java/com/example/
│           └── AppTest.java
├── pom.xml
├── database_setup.sql
└── README.md
```

## Prerequisites

Before running this application, ensure you have installed:

1. **Java Development Kit (JDK)** 11 or higher
   - Download from: https://www.oracle.com/java/technologies/downloads/

2. **Apache Maven** 3.6.0 or higher
   - Download from: https://maven.apache.org/download.cgi
   - Add Maven to system PATH

3. **MySQL Server** 8.0 or higher
   - Download from: https://dev.mysql.com/downloads/mysql/
   - Ensure MySQL service is running

4. **NetBeans IDE** (recommended) or any Java IDE
   - Download from: https://netbeans.apache.org/
   - Or use Eclipse, IntelliJ IDEA, VS Code with Java extensions

5. **Apache Tomcat** 9.0 or higher (if not included in IDE)
   - Download from: https://tomcat.apache.org/

## Installation & Setup

### Step 1: Clone or Extract the Project
```bash
# Extract the project files to your desired location
cd path/to/event-management
```

### Step 2: Database Configuration
1. Open MySQL command line or MySQL Workbench
2. Execute the SQL script to create the database and tables:
```bash
mysql -u root -p < database_setup.sql
```
Or run the commands from `database_setup.sql` directly in MySQL.

### Step 3: Update Database Connection
Edit `src/main/resources/META-INF/persistence.xml`:
- Update the JDBC URL if your database is on a different host/port
- Update username and password according to your MySQL configuration

```xml
<property name="javax.persistence.jdbc.url" 
    value="jdbc:mysql://localhost:3306/event_management?useSSL=false&amp;serverTimezone=UTC"/>
<property name="javax.persistence.jdbc.user" value="root"/>
<property name="javax.persistence.jdbc.password" value="your_password"/>
```

### Step 4: Build the Project
```bash
# Navigate to project directory
cd event-management

# Build using Maven
mvn clean package
```

### Step 5: Deploy the Application

#### Using NetBeans IDE:
1. Open the project in NetBeans
2. Right-click on the project → Properties
3. Set up Apache Tomcat as the application server
4. Right-click on the project → Run
5. The application URL: `http://localhost:8080/event-management/`

#### Using Maven with Tomcat Plugin:
```bash
# Add the Tomcat plugin to pom.xml (optional)
# Then deploy using Maven
mvn tomcat7:deploy
```

#### Using Tomcat Directly:
1. Copy the WAR file from `target/event-management.war`
2. Place it in Tomcat's `webapps` directory
3. Start Tomcat server
4. Access: `http://localhost:8080/event-management/`

## Usage

### 1. Home Page
- Access the application at `http://localhost:8080/event-management/`
- Navigation menu provides quick access to all features

### 2. Add Event
- Click on "Add Event" in the navigation
- Fill in the form:
  - Event name (at least 5 characters)
  - Date (select from date picker)
  - Venue
  - Number of seats available
- Click "Add Event" to save
- Form validates all inputs before submission

### 3. View Events
- Click on "View Events"
- See all events in table format
- Click "Delete" button to remove an event
- Confirmation dialog appears before deletion

### 4. Add Attendee
- Click on "Add Attendee"
- Fill in the form:
  - Attendee name (at least 3 characters)
  - Email address (valid format)
  - Select an event from dropdown
- Click "Add Attendee" to save
- Form validates all inputs

### 5. View Attendees
- Click on "View Attendees"
- Select an event from the dropdown
- View all attendees registered for that event
- Display shows ID, Name, and Email

## Validation Rules

### Event Validation
| Field | Rules | Error Message |
|-------|-------|---------------|
| Name | Required, Min 5 chars | "Event name must have at least 5 characters" |
| Date | Required | "Date cannot be null" |
| Venue | Required | "Venue cannot be empty" |
| Seats | Required, Positive number | "Seats available must be a positive number" |

### Attendee Validation
| Field | Rules | Error Message |
|-------|-------|---------------|
| Name | Required, Min 3 chars | "Name must be at least 3 characters" |
| Email | Required, Valid format | "Email must be in a valid email format" |
| Event | Required | "Event must be selected" |

## Database Schema

### events Table
```sql
CREATE TABLE events (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    date DATE NOT NULL,
    venue VARCHAR(100) NOT NULL,
    seats_available INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### attendees Table
```sql
CREATE TABLE attendees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    event_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE
);
```

## Configuration Files

### pom.xml
Maven configuration file specifying:
- Project dependencies (Servlet API, JSP, JPA, Hibernate, MySQL Driver)
- Build plugins (Maven War Plugin, Maven Compiler Plugin)
- Java version (11)

### persistence.xml
JPA configuration file specifying:
- Database driver and connection details
- Hibernate dialect and configuration
- ORM mapping classes

## Troubleshooting

### Issue: Database Connection Error
**Solution:**
1. Ensure MySQL server is running
2. Verify database name is `event_management`
3. Check username/password in persistence.xml
4. Verify JDBC URL format

### Issue: Class Not Found Exception
**Solution:**
1. Run `mvn clean install` to download all dependencies
2. Ensure all JAR files are in the classpath
3. Rebuild the project in IDE

### Issue: JSP Pages Not Found (404 Error)
**Solution:**
1. Ensure JSP files are in `src/main/webapp/`
2. Check Web Application Context is set correctly in IDE
3. Verify WAR file includes JSP files

### Issue: Validation Not Working
**Solution:**
1. Ensure Hibernate Validator is in dependencies
2. Check annotations are correctly placed in entity classes
3. Verify server-side validation in Servlets

## Performance Considerations

1. **Database Indexes**: Created on frequently searched columns (date, name, email)
2. **Connection Pooling**: EntityManagerFactory creates connections efficiently
3. **Query Optimization**: Named queries used where applicable
4. **Pagination**: Can be added for large datasets

## Security Considerations

1. **Input Validation**: All inputs validated on client and server-side
2. **SQL Injection**: JPA parameterized queries prevent SQL injection
3. **CSRF Protection**: Can be added using tokens
4. **XSS Prevention**: JSP automatically escapes output

## Future Enhancements

1. Add authentication and authorization
2. Implement pagination for large datasets
3. Add searching and filtering capabilities
4. Email notifications for event updates
5. Generate event reports and statistics
6. REST API endpoints
7. Mobile responsive improvements
8. Dark mode theme

## Support & Documentation

For more information:
- [Apache Tomcat Documentation](https://tomcat.apache.org/tomcat-9.0-doc/)
- [Hibernate JPA Documentation](https://hibernate.org/orm/documentation/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Java Servlet Specification](https://projects.eclipse.org/projects/ee4j.servlet)

## License

This project is provided for educational purposes.

## Contact

For questions or support, please contact the development team.

---

**Version**: 1.0  
**Last Updated**: 2024  
**Status**: Production Ready
