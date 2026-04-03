package controller;

import dao.DAO;
import model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Đường dẫn URL để truy cập vào trang này sẽ là: http://localhost:8080/Tên_Project/category
@WebServlet(name = "CategoryServlet", urlPatterns = {"/category"})
public class CategoryServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Gọi DAO để lấy toàn bộ danh sách Category
        DAO dao = new DAO();
        List<Category> list = dao.getAllCategories();
        
        // 2. Gắn danh sách này vào request với tên "listC"
        request.setAttribute("listC", list);
        
        // 3. Chuyển hướng sang trang category.jsp để hiển thị
        request.getRequestDispatcher("category.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}