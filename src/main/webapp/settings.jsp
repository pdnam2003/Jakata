<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cài Đặt</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f5f5f5;
        }
        
        .navbar {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 15px 30px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        .navbar h2 {
            font-size: 24px;
        }
        
        .navbar-links {
            display: flex;
            gap: 30px;
            align-items: center;
        }
        
        .navbar-links a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            transition: opacity 0.3s;
        }
        
        .navbar-links a:hover {
            opacity: 0.8;
        }
        
        .logout-btn {
            background: rgba(255, 255, 255, 0.2);
            padding: 8px 16px;
            border-radius: 5px;
            border: 1px solid white;
        }
        
        .logout-btn:hover {
            background: rgba(255, 255, 255, 0.3);
        }
        
        .container {
            max-width: 800px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .settings-section {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        
        .settings-section h1 {
            color: #333;
            margin-bottom: 30px;
            font-size: 28px;
        }
        
        .settings-group {
            margin-bottom: 30px;
            padding-bottom: 30px;
            border-bottom: 1px solid #eee;
        }
        
        .settings-group:last-child {
            border-bottom: none;
        }
        
        .settings-group h2 {
            color: #667eea;
            font-size: 18px;
            margin-bottom: 15px;
        }
        
        .form-group {
            margin-bottom: 15px;
        }
        
        .form-group label {
            display: block;
            margin-bottom: 5px;
            color: #555;
            font-weight: 500;
        }
        
        .form-group input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 14px;
        }
        
        .form-group input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 5px rgba(102, 126, 234, 0.3);
        }
        
        .btn-group {
            display: flex;
            gap: 10px;
        }
        
        .btn {
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
            transition: transform 0.2s;
        }
        
        .btn-save {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
        }
        
        .btn-save:hover {
            transform: translateY(-2px);
        }
        
        .btn-cancel {
            background: #ddd;
            color: #333;
        }
        
        .btn-cancel:hover {
            background: #ccc;
        }
        
        .info-box {
            background: #e7f3ff;
            border-left: 4px solid #2196F3;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        
        .info-box p {
            color: #0c5aa0;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <h2>Ứng Dụng Quản Lý</h2>
        <div class="navbar-links">
            <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
            <a href="${pageContext.request.contextPath}/settings">Cài Đặt</a>
            <a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng Xuất</a>
        </div>
    </div>
    
    <div class="container">
        <div class="settings-section">
            <h1>Cài Đặt Tài Khoản</h1>
            
            <div class="settings-group">
                <h2>📝 Thông Tin Cá Nhân</h2>
                <div class="info-box">
                    <p>Cập nhật thông tin cá nhân của bạn</p>
                </div>
                
                <form method="POST" action="#">
                    <div class="form-group">
                        <label for="name">Tên Đầy Đủ</label>
                        <input type="text" id="name" name="name" value="<%= user.getName() %>" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" value="<%= user.getEmail() %>" required>
                    </div>
                    
                    <div class="btn-group">
                        <button type="submit" class="btn btn-save">Lưu Thay Đổi</button>
                        <button type="reset" class="btn btn-cancel">Hủy</button>
                    </div>
                </form>
            </div>
            
            <div class="settings-group">
                <h2>🔐 Bảo Mật</h2>
                <div class="info-box">
                    <p>Thay đổi mật khẩu của bạn để bảo vệ tài khoản</p>
                </div>
                
                <form method="POST" action="#">
                    <div class="form-group">
                        <label for="old-password">Mật Khẩu Cũ</label>
                        <input type="password" id="old-password" name="old-password" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="new-password">Mật Khẩu Mới</label>
                        <input type="password" id="new-password" name="new-password" required>
                    </div>
                    
                    <div class="form-group">
                        <label for="confirm-password">Xác Nhận Mật Khẩu Mới</label>
                        <input type="password" id="confirm-password" name="confirm-password" required>
                    </div>
                    
                    <div class="btn-group">
                        <button type="submit" class="btn btn-save">Đổi Mật Khẩu</button>
                        <button type="reset" class="btn btn-cancel">Hủy</button>
                    </div>
                </form>
            </div>
            
            <div class="settings-group">
                <h2>⚡ Options</h2>
                <div class="form-group">
                    <label>
                        <input type="checkbox" checked> Nhận thông báo qua email
                    </label>
                </div>
                <div class="form-group">
                    <label>
                        <input type="checkbox" checked> Hiển thị trang hồ sơ công khai
                    </label>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
