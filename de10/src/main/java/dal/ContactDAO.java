package dal;

import model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {
    
    // Yêu cầu: Output all Contacts to ListContacts.jsp
    public List<Contact> getAllContacts() {
        List<Contact> list = new ArrayList<>();
        // Dùng JOIN để lấy được Name của Group thay vì chỉ lấy ID
        String sql = "SELECT c.*, g.name as groupName FROM Contact c JOIN GroupTable g ON c.groupId = g.id";
        Connection con = JDBCConnect.getJDBCConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Contact(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getInt("groupId"),
                    rs.getString("groupName"),
                    rs.getString("phoneNumber")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    // Yêu cầu: Add information to Contact table
    public void addContact(String fName, String lName, int gId, String phone) {
        String sql = "INSERT INTO Contact (firstName, lastName, groupId, phoneNumber) VALUES (?, ?, ?, ?)";
        Connection con = JDBCConnect.getJDBCConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, fName);
            st.setString(2, lName);
            st.setInt(3, gId);
            st.setString(4, phone);
            st.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // Yêu cầu: Remove contact
    public void deleteContact(int id) {
        String sql = "DELETE FROM Contact WHERE id = ?";
        Connection con = JDBCConnect.getJDBCConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}