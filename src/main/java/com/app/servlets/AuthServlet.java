@WebServlet({"/register", "/login", "/logout"})
public class AuthServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        if (path.equals("/register")) {
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
        } else if (path.equals("/login")) {
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        } else {
            request.getSession().invalidate();
            response.sendRedirect("login");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            if (path.equals("/register")) {
                User user = new User();
                user.setUsername(request.getParameter("username"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("password"));
                
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
                response.sendRedirect("login");
            } else if (path.equals("/login")) {
                String u = request.getParameter("username");
                String p = request.getParameter("password");
                
                User user = em.createQuery("SELECT u FROM User u WHERE u.username = :u AND u.password = :p", User.class)
                        .setParameter("u", u).setParameter("p", p).getSingleResult();
                
                request.getSession().setAttribute("user", user);
                response.sendRedirect("products");
            }
        } catch (Exception e) {
            request.setAttribute("error", "Invalid data or User already exists!");
            doGet(request, response);
        } finally { em.close(); }
    }
}