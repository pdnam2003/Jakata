package org.exam.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.ejb.StringProcessorEJB;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "StringProcessorServlet", urlPatterns = "/process-name")
public class StringProcessorServlet extends HttpServlet {

    @EJB
    private StringProcessorEJB stringProcessorEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");

        String name = request.getParameter("name");
        String result = stringProcessorEJB.processName(name);

        try (PrintWriter writer = response.getWriter()) {
            writer.println(result);
        }
    }
}
