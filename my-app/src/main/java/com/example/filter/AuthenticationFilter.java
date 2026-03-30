package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        // Paths that don't require authentication
        boolean isLoginPage = requestURI.endsWith("login.jsp");
        boolean isLoginServlet = requestURI.endsWith("LoginServlet");
        boolean isStaticResource = requestURI.endsWith(".css") || requestURI.endsWith(".js") || requestURI.endsWith(".jpg") 
                                || requestURI.endsWith(".png") || requestURI.endsWith(".gif");

        // If it's a static resource or login page/servlet, allow access
        if (isStaticResource || isLoginPage || isLoginServlet) {
            chain.doFilter(request, response);
            return;
        }

        // Check if user is logged in
        HttpSession session = httpRequest.getSession(false);
        String user = (session != null) ? (String) session.getAttribute("user") : null;

        if (user == null) {
            // User is not logged in, redirect to login page
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
        } else {
            // User is logged in, continue the request
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
