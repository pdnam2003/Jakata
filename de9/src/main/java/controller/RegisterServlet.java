@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String user = request.getParameter("username");
        String pass = request.getParameter("password");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        UserDAO dao = new UserDAO();
        dao.register(user, pass, email, address);
        
        // Sau khi đăng ký xong, quay về trang login
        response.sendRedirect("login.jsp");
    }
}