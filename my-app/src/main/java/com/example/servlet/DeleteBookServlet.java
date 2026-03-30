package com.example.servlet;

import com.example.dao.BookDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteBookServlet")
public class DeleteBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("id");
            if (idStr != null && !idStr.trim().isEmpty()) {
                Long bookId = Long.parseLong(idStr);
                bookDAO.deleteBook(bookId);
                request.setAttribute("success", "Book deleted successfully!");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Error deleting book: " + e.getMessage());
        }
        response.sendRedirect("ListBookServlet");
    }
}
