-- Tạo database
CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

-- Tạo bảng users
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Thêm dữ liệu mẫu
INSERT INTO users (email, password, name) VALUES 
('user1@example.com', 'password123', 'Nguyễn Văn A'),
('user2@example.com', 'password456', 'Trần Thị B'),
('user3@example.com', 'password789', 'Lê Văn C');
