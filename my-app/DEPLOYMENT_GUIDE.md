# Event Management System - NetBeans Deployment Guide

## Table of Contents
1. [Prerequisites](#prerequisites)
2. [Initial Setup](#initial-setup)
3. [Database Configuration](#database-configuration)
4. [Opening Project in NetBeans](#opening-project-in-netbeans)
5. [Configuring Application Server](#configuring-application-server)
6. [Building and Running](#building-and-running)
7. [Common Issues and Solutions](#common-issues-and-solutions)

---

## Prerequisites

### Step 1: Install Required Software

#### 1.1 Java Development Kit (JDK)
- **Version Required**: JDK 11 or higher
- **Download**: https://www.oracle.com/java/technologies/downloads/
- **Installation**:
  - Run the installer exe/dmg
  - Follow installation wizard
  - Note the installation path (e.g., `C:\Program Files\Java\jdk-11.0.x`)
- **Verify Installation**:
  ```cmd
  java -version
  javac -version
  ```

#### 1.2 Apache NetBeans IDE
- **Version Required**: NetBeans 12.0 or later
- **Download**: https://netbeans.apache.org/download/
- **Installation**:
  - Run the installer
  - Choose "Java EE" during installation
  - This will automatically download Apache Tomcat 10.x
- **Alternative**: Download NetBeans bundle with Tomcat included

#### 1.3 MySQL Server
- **Version Required**: MySQL 8.0 or higher
- **Download**: https://dev.mysql.com/downloads/mysql/
- **Installation Steps**:
  1. Run MySQL installer
  2. Choose "Server only" or "Complete" installation
  3. Install MySQL Server as a Windows Service
  4. Configure port (default 3306)
  5. Set root password
  6. Start MySQL service
- **Verify Installation**:
  - Open command line
  - Run `mysql --version`
  - Connect to MySQL: `mysql -u root -p`

#### 1.4 MySQL Workbench (Optional but Recommended)
- **Download**: https://dev.mysql.com/downloads/workbench/
- **Purpose**: GUI tool for MySQL database management

---

## Initial Setup

### Step 1: Extract Project Files
1. Extract the `event-management` project zip file to desired location
   - Example: `C:\Projects\event-management-system`

### Step 2: Verify Project Structure
Ensure the following structure exists:
```
event-management/
├── src/
│   ├── main/
│   │   ├── java/
│   │   ├── resources/
│   │   └── webapp/
│   └── test/
├── target/ (created during build)
├── pom.xml
├── README.md
└── database_setup.sql
```

---

## Database Configuration

### Step 1: Create Database
1. **Method 1: Using MySQL Command Line**
   - Open Command Prompt or PowerShell
   - Run: `mysql -u root -p`
   - Enter MySQL root password
   - Execute commands from `database_setup.sql`:
   ```sql
   CREATE DATABASE IF NOT EXISTS event_management;
   USE event_management;
   
   CREATE TABLE IF NOT EXISTS events (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       date DATE NOT NULL,
       venue VARCHAR(100) NOT NULL,
       seats_available INT NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       INDEX idx_date (date),
       INDEX idx_name (name)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
   
   CREATE TABLE IF NOT EXISTS attendees (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       event_id BIGINT NOT NULL,
       name VARCHAR(100) NOT NULL,
       email VARCHAR(100) NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (event_id) REFERENCES events(id) ON DELETE CASCADE ON UPDATE CASCADE,
       INDEX idx_event_id (event_id),
       INDEX idx_email (email),
       UNIQUE KEY unique_attendee_email_event (email, event_id)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
   ```

2. **Method 2: Using MySQL Workbench**
   - Connect to MySQL Server
   - File → Open SQL Script
   - Select `database_setup.sql`
   - Execute script (Ctrl+Shift+Enter)

3. **Method 3: Command Line Direct**
   ```cmd
   mysql -u root -p < path/to/database_setup.sql
   ```

### Step 2: Verify Database Creation
```sql
SHOW DATABASES;
USE event_management;
SHOW TABLES;
DESCRIBE events;
DESCRIBE attendees;
```

### Step 3: Update Credentials (if needed)
If your MySQL setup uses different credentials:
1. Navigate to: `src/main/resources/META-INF/persistence.xml`
2. Update the following lines:
   ```xml
   <property name="javax.persistence.jdbc.user" value="your_username"/>
   <property name="javax.persistence.jdbc.password" value="your_password"/>
   ```

---

## Opening Project in NetBeans

### Step 1: Launch NetBeans
1. Start NetBeans IDE
2. Wait for IDE to fully load

### Step 2: Open Existing Project
1. Go to **File → Open Project**
2. Navigate to the `event-management` folder
3. Select the project folder (containing `pom.xml`)
4. Click "Open Project"
5. Wait for NetBeans to load and index the project

### Step 3: Wait for Maven Indexing
- NetBeans will download Maven dependencies (may take 2-5 minutes)
- Watch for progress in the status bar at bottom
- Ensure no errors appear in the "Output" tab

### Step 4: Verify Project Loaded
- Expand project in left panel (Project Navigator)
- Verify folders and files are visible:
  - `src/main/java/` - Source code
  - `src/main/webapp/` - JSP files
  - `src/main/resources/` - Configuration files
  - `pom.xml` - Maven configuration

---

## Configuring Application Server

### Step 1: Verify Tomcat Installation
1. In NetBeans, go to **Tools → Options**
2. Click on **Java** in left panel
3. Select **Build, Compile** tab
4. Verify JDK is set to Java 11 or higher

### Step 2: Configure Apache Tomcat
1. Go to **Tools → Options**
2. Click on **Apache Tomcat** in left panel
3. If not listed, click "Add":
   - Set Tomcat Home: Navigate to Tomcat installation folder
   - Set Tomcat Base: Can be same as Home or custom
   - Set Shutdown Port: Default 8005
   - Click OK

### Step 3: Assign Server to Project
1. Right-click on the `event-management` project
2. Select **Properties**
3. In left panel, click **Run**
4. Server dropdown: Select **Apache Tomcat [version]**
5. Context Path: `/event-management`
6. Click OK

---

## Building and Running

### Step 1: Clean and Build Project
1. Right-click on `event-management` project
2. Select **Clean and Build**
3. Wait for build to complete
4. Check "Output" tab for success message:
   ```
   [INFO] BUILD SUCCESS
   ```

### Step 2: Run Project
1. Right-click on `event-management` project
2. Click **Run**
   - Or press **F6**
3. NetBeans will:
   - Start Tomcat server
   - Deploy WAR file
   - Open browser to application URL

### Step 3: Access Application
- Default URL: `http://localhost:8080/event-management/`
- Home page should load with navigation menu
- If not automatic, manually navigate to the URL

---

## Application Testing

### Step 1: Test Event Management
1. Navigate to "Add Event" page
2. Fill in form:
   - Name: `Test Concert Event` (must be 5+ chars)
   - Date: Select any future date
   - Venue: `Main Hall`
   - Seats: `100`
3. Click "Add Event"
4. Verify success message and redirect to form
5. Click "View Events" to see added event in table

### Step 2: Test Attendee Management
1. Navigate to "Add Attendee" page
2. Fill in form:
   - Name: `John Doe` (must be 3+ chars)
   - Email: `john@example.com`
   - Event: Select the event created above
3. Click "Add Attendee"
4. Verify success message
5. Navigate to "View Attendees"
6. Select the event to see attendees list

### Step 3: Test Validation
1. Try adding event with name less than 5 characters
   - Should show error message
2. Try adding attendee with invalid email
   - Should show error message
3. Try adding event with negative seats
   - Should show error message

### Step 4: Test Database Deletion
1. Go to "View Events"
2. Click "Delete" button next to an event
3. Confirm deletion in popup
4. Verify event removed from list

---

## Common Issues and Solutions

### Issue 1: "Port 8080 Already in Use"
**Solution:**
1. Stop other Tomcat instances
2. Change Tomcat port in NetBeans:
   - Tools → Options → Apache Tomcat
   - Edit Connector Port (change from 8080 to 8081, etc.)
3. Or find and kill process using port 8080:
   ```cmd
   netstat -ano | findstr :8080
   taskkill /PID <process_id> /F
   ```

### Issue 2: "MySQL Connection Error"
**Solution:**
1. Verify MySQL is running:
   ```cmd
   sc query mysql80
   ```
2. Start MySQL if not running:
   ```cmd
   net start MySQL80
   ```
3. Check credentials in `persistence.xml`
4. Test connection:
   ```cmd
   mysql -u root -p -h localhost -P 3306
   ```

### Issue 3: "JDBC Driver Not Found"
**Solution:**
1. Maven should download MySQL connector
2. If not, clean and rebuild:
   ```
   Right-click project → Clean and Build
   ```
3. Check if mysql-connector-java in dependencies (pom.xml)

### Issue 4: "Build Fails"
**Solution:**
1. Enable output details:
   - Tools → Options → Miscellaneous → Maven
   - Check "Show execution details on output"
2. Run Maven update:
   - Right-click project → Maven → Update Project
3. Check error messages in Output tab
4. Try cleaning: Right-click → Clean

### Issue 5: "JSP File Not Found (404 Error)"
**Solution:**
1. Verify JSP files in `src/main/webapp/`:
   - index.jsp, addEvent.jsp, etc. should exist
2. Rebuild project (Clean and Build)
3. Check context path in project properties
4. Restart Tomcat:
   - Tools → Servers → Apache Tomcat → Stop
   - Wait 5 seconds
   - Run project again

### Issue 6: "Persistence Unit Not Found"
**Solution:**
1. Verify `persistence.xml` exists in:
   - `src/main/resources/META-INF/persistence.xml`
2. Check file name exact spelling
3. Verify XML syntax is correct
4. Rebuild project

### Issue 7: "EntityManager Connection Error"
**Solution:**
1. Check database exists: `SHOW DATABASES;`
2. Verify tables created: `USE event_management; SHOW TABLES;`
3. Check MySQL user permissions
4. Test URL in persistence.xml:
   ```
   jdbc:mysql://localhost:3306/event_management
   ```

---

## Debugging

### Enable Debug Mode
1. Right-click project → **Debug**
   - Or press **Ctrl+Shift+F5**
2. Set breakpoints by clicking line numbers
3. Use Debug toolbar to step through code

### View Server Logs
1. Tools → Servers → Apache Tomcat
2. Click "Stop" then "Start" to see logs
3. Check `catalina.out` file in Tomcat logs directory

### Check Application Logs
1. In NetBeans Output tab (next to Project tab)
2. Look for WARN, ERROR messages
3. Check full stack traces for issues

---

## Troubleshooting Checklist

Before reporting issues, verify:
- [ ] Java version is 11 or higher
- [ ] MySQL is running and accessible
- [ ] Database `event_management` exists
- [ ] Tables `events` and `attendees` exist
- [ ] Port 8080 is not blocked
- [ ] All dependencies downloaded (check Maven)
- [ ] No errors in NetBeans Output tab
- [ ] Firewall allows Tomcat
- [ ] persistence.xml has correct database credentials

---

## Project Maintenance

### Regular Tasks
1. **Backup Database**:
   ```cmd
   mysqldump -u root -p event_management > backup.sql
   ```

2. **Restart Tomcat**:
   - In NetBeans: Tools → Servers → Apache Tomcat → Stop/Start

3. **Update Dependencies**:
   - Right-click project → Maven → Update Project

4. **Check for Errors**:
   - Watch NetBeans Output tab regularly

---

## Deployment to Production

### Steps:
1. Build final WAR:
   ```
   Right-click project → Package
   ```
2. WAR file location: `target/event-management.war`
3. Deploy to production Tomcat:
   - Copy WAR to production Tomcat's `webapps/` folder
   - Server auto-deploys application
4. Verify at: `http://prod-server:8080/event-management/`

---

## Support Resources

- **NetBeans Documentation**: https://netbeans.apache.org/
- **Apache Tomcat**: https://tomcat.apache.org/tomcat-9.0-doc/
- **Hibernate/JPA**: https://hibernate.org/
- **MySQL**: https://dev.mysql.com/doc/
- **Maven**: https://maven.apache.org/documentation.html

---

**Document Version**: 1.0  
**Last Updated**: 2024  
**Status**: Complete
