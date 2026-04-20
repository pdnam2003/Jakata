package com.app.servlets;

import com.app.utils.ExamDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-exam")
public class DeleteExamServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                Long id = Long.parseLong(idStr);
                ExamDAO.deleteExam(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        // Redirect to exam list page
        response.sendRedirect(request.getContextPath() + "/exams");
    }
}
