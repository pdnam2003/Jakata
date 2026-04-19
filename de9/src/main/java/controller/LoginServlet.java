// Trong phương thức doPost
String user = request.getParameter("username");
String pass = request.getParameter("password");

UserDAO dao = new UserDAO();
User account = dao.login(user, pass);

if (account != null) {
    HttpSession session = request.getSession();
    session.setAttribute("acc", account); // Lưu object user vào session
    response.sendRedirect("home.jsp");
} else {
    request.setAttribute("mess", "Wrong username or password!");
    request.getRequestDispatcher("login.jsp").forward(request, response);
}