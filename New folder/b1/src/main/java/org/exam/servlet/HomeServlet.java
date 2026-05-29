package org.exam.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.exam.ejb.VisitorCounterEJB;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "HomeServlet", urlPatterns = "/home")
public class HomeServlet extends HttpServlet {

    @EJB
    private VisitorCounterEJB visitorCounterEJB;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");

        int visitorNumber = visitorCounterEJB.incrementAndGet();

        try (PrintWriter writer = response.getWriter()) {
            writer.printf("Bạn là người thứ %d truy cập vào trang web này.", visitorNumber);
        }
    }
}
