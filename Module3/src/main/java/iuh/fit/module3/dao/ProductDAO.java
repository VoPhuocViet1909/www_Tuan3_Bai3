package iuh.fit.module3.dao;

import iuh.fit.module3.model.Product;
import iuh.fit.module3.util.DBUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private DBUtil dbUtil;

    public ProductDAO(DataSource dataSource) {
        dbUtil = new DBUtil(dataSource
        );
    }

    //REAL ALL
    public List<Product> getAllProducts(){
        List <Product> list = new ArrayList<>();
        String sql = "SELECT  * FROM products";
        try (Connection conn = dbUtil.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String model = rs.getString("MODEL");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String description = rs.getString("DESCRIPTION");
                String imageUrl = rs.getString("IMGURL");
                Product p = new Product(id, model, price, quantity, description, imageUrl);
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  list;
    }

    //read by id
    public  Product getProductById(int id) {
        Product p = null;
        String sql = "SELECT * FROM products WHERE ID = ?";
        try (Connection conn = dbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String model = rs.getString("MODEL");
                double price = rs.getDouble("PRICE");
                int quantity = rs.getInt("QUANTITY");
                String description = rs.getString("DESCRIPTION");
                String imageUrl = rs.getString("IMGURL");
                p = new Product(id, model, price, quantity, description, imageUrl);
                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
