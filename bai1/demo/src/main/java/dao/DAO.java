package dao;

import context.DBContext;
import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 1. Lấy tất cả Category để hiển thị ra trang category.jsp (Câu 1 - ý 1)
    public List<Category> getAllCategories() {
        List<Category> list = new ArrayList<>();
        String query = "SELECT * FROM categories";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("description")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 2. Lấy danh sách Product theo Category ID (Câu 1 - ý 2)
    public List<Product> getProductsByCategoryId(int cid) {
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM products WHERE category_id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), 
                        rs.getDouble("price"), rs.getString("description"), rs.getInt("category_id")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3. Lấy 1 Product cụ thể để lấy tên hiển thị ở trang assign.jsp
    public Product getProductById(int pid) {
        String query = "SELECT * FROM products WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), 
                        rs.getDouble("price"), rs.getString("description"), rs.getInt("category_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Update Category mới cho Product (Xử lý khi nhấn nút Update ở assign.jsp)
    public void updateProductCategory(int productId, int newCategoryId) {
        String query = "UPDATE products SET category_id = ? WHERE id = ?";
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setInt(1, newCategoryId);
            ps.setInt(2, productId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}