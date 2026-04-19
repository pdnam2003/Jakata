package dal;

import model.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO {
    public List<Group> getAllGroups() {
        List<Group> list = new ArrayList<>();
        String sql = "SELECT * FROM GroupTable";
        Connection con = JDBCConnect.getJDBCConnection();
        try {
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Group(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCConnect.closeConnection(con);
        }
        return list;
    }public void addGroup(String name, String description) {
    String sql = "INSERT INTO GroupTable (name, description) VALUES (?, ?)";
    Connection con = JDBCConnect.getJDBCConnection();
    try {
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1, name);
        st.setString(2, description);
        st.executeUpdate();
    } catch (SQLException e) { e.printStackTrace(); }
}
}