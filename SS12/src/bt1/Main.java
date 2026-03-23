package bt1;

import utils.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getConnection();
//        String code = "doc1";
//        String pass = "' OR '1'='1"; // hack
//
//        String sql = "SELECT * FROM Doctors WHERE code = '" + code + "' AND pass = '" + pass + "'";
//
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery(sql);
//
//        //  LỖI: nối chuỗi trực tiếp → dính SQL Injection
//        if (rs.next()) {
//            System.out.println("Login success");
//        }

        String code = "doc1";
        String pass = "' OR '1'='1";

        String sql = "SELECT * FROM Doctors WHERE code = ? AND pass = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, code);
        ps.setString(2, pass);

        ResultSet rs = ps.executeQuery();

        // FIX: dùng PreparedStatement → chống SQL Injection
        if (rs.next()) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
        }
    }
}