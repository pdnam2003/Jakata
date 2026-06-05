package com.example.web;

import com.example.entity.Student;
import com.example.service.StudentBeanRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    @EJB
    private StudentBeanRemote studentBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("new".equals(action)) {
            req.getRequestDispatcher("/create.jsp").forward(req, resp);
            return;
        } else if ("edit".equals(action)) {
            String roll = req.getParameter("roll");
            if (roll != null) {
                try {
                    Student s = studentBean.getStudentByRoll(roll);
                    req.setAttribute("student", s);
                } catch (Exception e) {
                    req.setAttribute("error", "Invalid roll number");
                }
            }
            req.getRequestDispatcher("/create.jsp").forward(req, resp);
            return;
        } else if ("delete".equals(action)) {
            String roll = req.getParameter("roll");
            if (roll != null) {
                try {
                    studentBean.deleteStudent(roll);
                    req.setAttribute("message", "Deleted successfully");
                } catch (Exception e) {
                    req.setAttribute("error", "Error deleting student: " + e.getMessage());
                }
            }
        }
        List<Student> list = studentBean.getAllStudents();
        req.setAttribute("students", list);
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String roll = req.getParameter("rollnumber");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String ageStr = req.getParameter("age");

        try {
            int age = ageStr == null || ageStr.isEmpty() ? 0 : Integer.parseInt(ageStr);
            String existing = req.getParameter("existing"); // if editing
            if (existing == null || existing.isEmpty()) {
                studentBean.addStudent(roll, name, email, age);
            } else {
                studentBean.updateStudent(roll, name, email, age);
            }
        } catch (Exception e) {
            req.setAttribute("error", "Operation failed: " + e.getMessage());
            req.getRequestDispatcher("/create.jsp").forward(req, resp);
            return;
        }
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}

