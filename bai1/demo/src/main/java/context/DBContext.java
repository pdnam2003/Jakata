package context;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBContext {
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://localhost:4306;databaseName=BaiMoi;encrypt=true;trustServerCertificate=true;";
        
        // Load driver JDBC
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
      
        return DriverManager.getConnection(url, "sa", "1234567"); 
    }
    
    // Hàm main để test thử xem kết nối thành công chưa (chuột phải chọn Run file này)
    public static void main(String[] args) {
        try {
            System.out.println(new DBContext().getConnection());
            System.out.println("Kết nối SQL Server THÀNH CÔNG!");
        } catch (Exception e) {
            System.out.println("Lỗi kết nối: " + e.getMessage());
        }
    }
}