package dal;

import model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO extends DBContext {

    // Kiểm tra login (Question 1.2)
    public User login(String user, String pass) {
        String sql = "SELECT * FROM UserAcc WHERE username = ? AND password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("password"), 
                               rs.getString("email"), rs.getString("address"));
            }
        } catch (Exception e) { System.out.println(e); }
        return null;
    }

    // Đăng ký tài khoản mới (Question 1.4.b)
    public void register(String user, String pass, String email, String address) {
        String sql = "INSERT INTO UserAcc VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, user);
            st.setString(2, pass);
            st.setString(3, email);
            st.setString(4, address);
            st.executeUpdate();
        } catch (Exception e) { System.out.println(e); }
    }
}