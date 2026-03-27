package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        UserDAO userDAO = new UserDAO();
        
        // Không còn SQLException nữa vì DAO đã xử lý bằng JPA
        User user = userDAO.login(email, password);
        
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user); // Lưu toàn bộ đối tượng User
            session.setAttribute("userId", user.getId()); // Lưu ID (optional)
            
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("error", "Email hoặc password không đúng");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}