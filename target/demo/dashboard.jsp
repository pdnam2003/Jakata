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
    <title>Dashboard</title>
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
            max-width: 1200px;
            margin: 30px auto;
            padding: 0 20px;
        }
        
        .welcome-section {
            background: white;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 30px;
        }
        
        .welcome-section h1 {
            color: #333;
            margin-bottom: 10px;
        }
        
        .welcome-section p {
            color: #666;
            font-size: 16px;
        }
        
        .dashboard-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
        }
        
        .dashboard-card {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            transition: transform 0.2s;
        }
        
        .dashboard-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.15);
        }
        
        .dashboard-card h3 {
            color: #667eea;
            margin-bottom: 10px;
        }
        
        .dashboard-card p {
            color: #666;
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
        <div class="welcome-section">
            <h1>Xin chào, <%= user.getName() %>!</h1>
            <p>Email: <%= user.getEmail() %></p>
        </div>
        
        <div class="dashboard-grid">
            <div class="dashboard-card">
                <h3>🏠 Trang Chủ</h3>
                <p>Đây là trang chính của ứng dụng. Bạn đã đăng nhập thành công!</p>
            </div>
            
            <div class="dashboard-card">
                <h3>📊 Thống Kê</h3>
                <p>Xem các thống kê và báo cáo của bạn</p>
            </div>
            
            <div class="dashboard-card">
                <h3>📋 Danh Sách</h3>
                <p>Quản lý các mục của bạn</p>
            </div>
            
            <div class="dashboard-card">
                <h3>⚙️ Cài Đặt</h3>
                <p>Cập nhật cài đặt và thông tin cá nhân</p>
            </div>
        </div>
    </div>
</body>
</html>
