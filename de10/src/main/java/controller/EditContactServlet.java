@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    // doGet: Lấy dữ liệu cũ hiển thị lên form
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ContactDAO dao = new ContactDAO();
        request.setAttribute("contact", dao.getContactById(id));
        request.getRequestDispatcher("EditContact.jsp").forward(request, response);
    }

    // doPost: Lưu dữ liệu sau khi người dùng nhấn nút Edit
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        int gId = Integer.parseInt(request.getParameter("groupId"));
        String phone = request.getParameter("phoneNumber");

        new ContactDAO().updateContact(id, fName, lName, gId, phone);
        response.sendRedirect("list-contacts");
    }
    
}