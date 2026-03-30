package com.example.servlet;

import com.example.dao.BookDAO;
import com.example.entity.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListBookServlet")
public class ListBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("list.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error retrieving books: " + e.getMessage());
            request.getRequestDispatcher("list.jsp").forward(request, response);
        }
    }
}
