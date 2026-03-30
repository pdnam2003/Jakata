package com.example.servlet;

import com.example.dao.BookDAO;
import com.example.dao.CategoryDAO;
import com.example.entity.Book;
import com.example.entity.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/InsertBookServlet")
public class InsertBookServlet extends HttpServlet {
    private BookDAO bookDAO = new BookDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Error loading categories: " + e.getMessage());
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String categoryIdStr = request.getParameter("categoryId");
        String priceStr = request.getParameter("price");
        String description = request.getParameter("description");
        String isbn = request.getParameter("isbn");

        List<String> errors = new ArrayList<>();

        // Validation
        if (title == null || title.trim().isEmpty()) {
            errors.add("Title cannot be empty");
        } else if (title.trim().length() < 3) {
            errors.add("Title must be at least 3 characters");
        }

        if (categoryIdStr == null || categoryIdStr.trim().isEmpty()) {
            errors.add("Category must be selected");
        }

        if (priceStr == null || priceStr.trim().isEmpty()) {
            errors.add("Price cannot be empty");
        } else {
            try {
                BigDecimal price = new BigDecimal(priceStr);
                if (price.signum() <= 0) {
                    errors.add("Price must be a positive number");
                }
            } catch (NumberFormatException e) {
                errors.add("Price must be a valid number");
            }
        }

        if (!errors.isEmpty()) {
            try {
                List<Category> categories = categoryDAO.getAllCategories();
                request.setAttribute("categories", categories);
            } catch (Exception e) {
                // Ignore
            }
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("insert.jsp").forward(request, response);
            return;
        }

        try {
            Long categoryId = Long.parseLong(categoryIdStr);
            Category category = categoryDAO.getCategoryById(categoryId);

            if (category == null) {
                errors.add("Selected category not found");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("insert.jsp").forward(request, response);
                return;
            }

            Book book = new Book();
            book.setTitle(title.trim());
            book.setAuthor(author != null ? author.trim() : "");
            book.setCategory(category);
            book.setPrice(new BigDecimal(priceStr));
            book.setDescription(description != null ? description.trim() : "");
            book.setIsbn(isbn != null ? isbn.trim() : "");

            bookDAO.addBook(book);

            request.setAttribute("success", "Book added successfully!");
            List<Category> categories = categoryDAO.getAllCategories();
            request.setAttribute("categories", categories);
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            errors.add("Invalid category ID or price");
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        } catch (Exception e) {
            errors.add("Error: " + e.getMessage());
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        }
    }
}
