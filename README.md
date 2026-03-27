# Hệ Thống Đăng Nhập - Hướng Dẫn Sử Dụng

## 📋 Mô Tả
Dự án này là một ứng dụng web Java (JSP/Servlet) với chức năng đăng nhập, quản lý session và kiểm tra quyền truy cập.

## 🎯 Chức Năng
- ✅ Đăng nhập bằng email/password
- ✅ Lưu session sau khi đăng nhập
- ✅ Kiểm tra quyền truy cập (bảo vệ các trang: /dashboard, /settings)
- ✅ Đăng xuất và xóa session
- ✅ Giao diện responsive

## 🔧 Cấu Hình Database

### 1. Tạo Database MySQL
```sql
CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (email, password, name) VALUES 
('user1@example.com', 'password123', 'Nguyễn Văn A'),
('user2@example.com', 'password456', 'Trần Thị B'),
('user3@example.com', 'password789', 'Lê Văn C');
```

### 2. Cấu Hình Kết Nối Database
File: `src/main/java/com/example/DatabaseConnection.java`

```java
private static final String URL = "jdbc:mysql://localhost:3306/mydb";
private static final String USER = "root";
private static final String PASSWORD = "root";
```

Thay đổi URL, USER, PASSWORD theo cấu hình của bạn.

## 📁 Cấu Trúc Dự Án

```
demo/
├── pom.xml
├── src/
│   └── main/
│       ├── java/com/example/
│       │   ├── DatabaseConnection.java      (Kết nối DB)
│       │   ├── User.java                    (Model User)
│       │   ├── UserDAO.java                 (Data Access Object)
│       │   ├── LoginServlet.java            (Xử lý đăng nhập)
│       │   ├── LogoutServlet.java           (Xử lý đăng xuất)
│       │   ├── DashboardServlet.java        (Trang dashboard)
│       │   ├── SettingsServlet.java         (Trang settings)
│       │   └── AuthenticationFilter.java    (Kiểm tra session)
│       └── webapp/
│           ├── index.jsp                     (Chuyển hướng đến login)
│           ├── login.jsp                     (Trang đăng nhập)
│           ├── dashboard.jsp                 (Trang dashboard)
│           ├── settings.jsp                  (Trang cài đặt)
│           └── WEB-INF/
│               └── web.xml                   (Cấu hình servlet, filter)
```

## 🚀 Cách Chạy

### 1. Build Project
```bash
mvn clean install
```

### 2. Deploy lên Server
- Sử dụng Tomcat hoặc JBoss
- Copy file `.war` từ `target/` vào thư mục `webapps`

### 3. Truy Cập Ứng Dụng
```
http://localhost:8080/demo/
```

Sẽ tự động chuyển hướng đến: `http://localhost:8080/demo/login`

## 👤 Tài Khoản Mẫu

| Email | Password | Tên |
|-------|----------|-----|
| user1@example.com | password123 | Nguyễn Văn A |
| user2@example.com | password456 | Trần Thị B |
| user3@example.com | password789 | Lê Văn C |

## 🔐 Quy Trình Đăng Nhập

1. **User truy cập trang chưa đăng nhập** → Được chuyển hướng đến `/login`
2. **Nhập email/password** → LoginServlet xử lý
3. **Kiểm tra trong database** → Nếu đúng, tạo session
4. **Lưu thông tin vào session** → User ID, User object
5. **Chuyển hướng đến /dashboard** → Dashboard Servlet xử lý
6. **AuthenticationFilter** → Kiểm tra session có tồn tại không
7. **Nếu session hợp lệ** → Cho phép truy cập
8. **Nếu session không hợp lệ** → Chuyển hướng đến `/login`

## 🛡️ Security Features

- **Session Management**: Lưu trữ user info trong session
- **URL Protection**: Filter kiểm tra tất cả request
- **Session Timeout**: 30 phút (có thể thay đổi trong web.xml)
- **HttpOnly Cookie**: Bảo vệ chống XSS attacks

## 📝 Những File Quan Trọng

### DatabaseConnection.java
- Hướng dẫn: Đây là class tĩnh quản lý kết nối database
- Vị trí: `src/main/java/com/example/`
- Cấu hình: Thay đổi URL, USER, PASSWORD

### AuthenticationFilter.java
- Kiểm tra session trên tất cả request
- Cho phép các URL: /login, /index.jsp, context root
- Bảo vệ các URL khác

### web.xml
- Đăng ký servlet: LoginServlet, DashboardServlet, SettingsServlet, LogoutServlet
- Đăng ký filter: AuthenticationFilter
- Cấu hình session timeout: 30 phút

## 🐛 Troubleshooting

### Lỗi: "MySQL Driver not found"
- Đảm bảo MySQL connector JAR có trong classpath
- Kiểm tra pom.xml có dependency mysql-connector-java

### Lỗi: "Connection refused"
- Kiểm tra MySQL service có chạy không
- Kiểm tra URL, username, password trong DatabaseConnection

### Bị redirect vô hạn
- Kiểm tra filter configuration trong web.xml
- Đảm bảo /login được exclude từ filter

## 🎨 Giao Diện

- Login page: Đơn giản, responsive
- Dashboard: Greeting user, các card thông tin
- Settings: Cập nhật thông tin cá nhân, đổi mật khẩu
- Navigation bar: Liên kết giữa các trang, nút đăng xuất

## 📞 Hỗ Trợ

Nếu có bất kỳ vấn đề nào, vui lòng kiểm tra:
1. Database connection
2. Maven dependencies
3. Tomcat server logs
4. Browser console

---
**Created**: 2026  
**Version**: 1.0
