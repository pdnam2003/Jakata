package com.example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        String path = req.getRequestURI();
        
        // Bỏ qua Filter đối với các trang tĩnh (CSS/JS) và trang công khai (Login/Index)
        if (path.contains("/login") || path.endsWith(".css") || path.endsWith(".js") 
            || path.contains("/index.jsp") || path.equals(req.getContextPath() + "/")) {
            chain.doFilter(request, response);
            return;
        }
        
        // Kiểm tra session cho các URL cần bảo vệ
        HttpSession session = req.getSession(false);
        
        // Đồng nhất kiểm tra object "user" thay vì chỉ "userId"
        if (session == null || session.getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Cho phép đi tiếp đến Servlet đích nếu đã đăng nhập
        chain.doFilter(request, response);
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
}