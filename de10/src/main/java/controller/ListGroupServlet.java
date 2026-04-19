@WebServlet("/list-groups")
public class ListGroupServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        GroupDAO dao = new GroupDAO();
        request.setAttribute("groups", dao.getAllGroups());
        request.getRequestDispatcher("ListGroups.jsp").forward(request, response);
    }
}