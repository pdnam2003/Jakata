@echo off
REM Script thiết lập hệ thống đăng nhập cho Windows

echo =========================================
echo Hệ Thống Đăng Nhập - Hướng Dẫn Thiết Lập
echo =========================================
echo.

echo 📋 BƯỚC 1: Chuẩn Bị
echo ✓ Đảm bảo MySQL/MariaDB đang chạy
echo ✓ Đảm bảo Java JDK 8+ đã cài đặt
echo ✓ Đảm bảo Maven đã cài đặt
echo.

echo 🔧 BƯỚC 2: Tạo Database
echo Chạy lệnh SQL sau trong MySQL:
echo.
echo CREATE DATABASE IF NOT EXISTS mydb;
echo USE mydb;
echo.
echo CREATE TABLE IF NOT EXISTS users (
echo     id INT PRIMARY KEY AUTO_INCREMENT,
echo     email VARCHAR(100) NOT NULL UNIQUE,
echo     password VARCHAR(255) NOT NULL,
echo     name VARCHAR(100) NOT NULL,
echo     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
echo );
echo.
echo INSERT INTO users (email, password, name) VALUES
echo ('user1@example.com', 'password123', 'Nguyễn Văn A'),
echo ('user2@example.com', 'password456', 'Trần Thị B'),
echo ('user3@example.com', 'password789', 'Lê Văn C');
echo.

echo 📝 BƯỚC 3: Cấu Hình Kết Nối Database
echo Mở file: src\main\java\com\example\DatabaseConnection.java
echo Thay đổi các giá trị sau:
echo.
echo private static final String URL = "jdbc:mysql://localhost:3306/mydb";
echo private static final String USER = "root";
echo private static final String PASSWORD = "root";
echo.

echo 🏗️ BƯỚC 4: Build Project
echo Chạy lệnh:
echo mvn clean install
echo.

echo 🚀 BƯỚC 5: Deploy trên Tomcat
echo 1. Sao chép file demo.war từ target\ vào TOMCAT\webapps\
echo 2. Khởi động lại Tomcat
echo.

echo 📱 BƯỚC 6: Truy Cập Ứng Dụng
echo Mở browser và vào: http://localhost:8080/demo/
echo.

echo 👤 ĐĂNG NHẬP VỚI TÀI KHOẢN MẪU:
echo Email: user1@example.com
echo Password: password123
echo.

echo ✅ HOÀN TẤT!
echo.
pause
