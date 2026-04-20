package com.app.servlets;

import com.app.entities.Exam;
import com.app.utils.ExamDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/exams")
public class ExamListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Exam> exams = ExamDAO.getAllExams();
        request.setAttribute("exams", exams);
        request.getRequestDispatcher("/WEB-INF/views/examList.jsp").forward(request, response);
    }
}
