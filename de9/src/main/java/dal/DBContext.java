package dal;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    /* Thay đổi thông số theo máy của bạn */
    protected Connection connection;
    public DBContext() {
        try {
            String user = "root";
            String pass = "123456";
            String url = "jdbc:mysql://localhost:3306/ExamData";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}