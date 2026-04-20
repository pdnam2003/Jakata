package com.app.servlets;

import com.app.entities.Exam;
import com.app.utils.ExamDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/new-exam")
public class NewExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/newExam.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String durationStr = request.getParameter("duration");

        // Validation
        Map<String, String> errors = new HashMap<>();

        // Validate name
        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "Exam name is required");
        }

        // Validate description
        if (description == null || description.trim().isEmpty()) {
            errors.put("description", "Description is required");
        }

        // Validate duration
        if (durationStr == null || durationStr.trim().isEmpty()) {
            errors.put("duration", "Duration is required");
        } else {
            try {
                int duration = Integer.parseInt(durationStr);
                if (duration <= 0) {
                    errors.put("duration", "Duration must be a positive number");
                }
            } catch (NumberFormatException e) {
                errors.put("duration", "Duration must be a valid number");
            }
        }

        // If there are errors, redirect back to form with errors and values
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("duration", durationStr);
            request.getRequestDispatcher("/WEB-INF/views/newExam.jsp").forward(request, response);
            return;
        }

        // Create new exam
        Exam exam = new Exam(name.trim(), description.trim(), Integer.parseInt(durationStr));
        Exam createdExam = ExamDAO.createExam(exam);

        if (createdExam != null) {
            // Redirect to exam list page
            response.sendRedirect(request.getContextPath() + "/exams");
        } else {
            request.setAttribute("error", "Failed to create exam. Please try again.");
            request.setAttribute("name", name);
            request.setAttribute("description", description);
            request.setAttribute("duration", durationStr);
            request.getRequestDispatcher("/WEB-INF/views/newExam.jsp").forward(request, response);
        }
    }
}
