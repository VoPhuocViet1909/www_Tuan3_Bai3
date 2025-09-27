package iuh.fit.bai2_lan1.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import iuh.fit.bai2_lan1.model.*;

public class AccountUtil {

    private DataSource datasource;

    public AccountUtil(DataSource datasource) throws Exception {
        this.datasource = datasource;
    }

    // Lấy danh sách account
    public List<Account> getAccounts() throws Exception {
        List<Account> accounts = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = datasource.getConnection();
            String sql = "SELECT * FROM accounts ORDER BY ID";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String fname = rs.getString("FIRSTNAME");
                String lname = rs.getString("LASTNAME");
                String email = rs.getString("EMAIL");
                String password = rs.getString("PASSWORD");
                Date dateofbirth = rs.getDate("DATEOFBIRTH");
                String gder = rs.getString("GENDER");

                Account acc = new Account(fname, lname, email, password, (java.sql.Date) dateofbirth, gder);
                accounts.add(acc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    // Thêm account
    public void addAccount(Account acc) throws Exception {
        String sql = "INSERT INTO accounts (FIRSTNAME, LASTNAME, EMAIL, PASSWORD, DATEOFBIRTH, GENDER) "
                + "VALUES (?, ?, ?, ?, ?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = datasource.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, acc.getFirstname());
            ps.setString(2, acc.getLastname());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPassword());
            ps.setDate(5, acc.getDateOfBirth());
            ps.setString(6, acc.getGender());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
