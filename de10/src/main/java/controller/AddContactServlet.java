package controller;

import dal.ContactDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Lấy dữ liệu từ form
        String fName = request.getParameter("firstName");
        String lName = request.getParameter("lastName");
        String gIdStr = request.getParameter("groupId");
        String phone = request.getParameter("phoneNumber");

        // 1. Validation: Yêu cầu nhập đầy đủ (1 Point)
        if (fName.isEmpty() || lName.isEmpty() || phone.isEmpty()) {
            request.setAttribute("error", "Require data for all text fields!");
            request.getRequestDispatcher("AddContact.jsp").forward(request, response);
            return;
        }

        // 2. Validation: Số điện thoại phải là số (0.5 Point)
        if (!phone.matches("\\d+")) {
            request.setAttribute("error", "Value for Phone Number must be numerical!");
            request.getRequestDispatcher("AddContact.jsp").forward(request, response);
            return;
        }

        // 3. Nếu mọi thứ ổn -> Thêm vào DB (2 Point)
        ContactDAO dao = new ContactDAO();
        dao.addContact(fName, lName, Integer.parseInt(gIdStr), phone);

        // 4. Chuyển hướng sang trang danh sách (0.5 Point)
        response.sendRedirect("list-contacts");
    }
}