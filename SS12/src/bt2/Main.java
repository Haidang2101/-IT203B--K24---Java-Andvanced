package bt2;

import utils.DatabaseConnection;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Connection conn = DatabaseConnection.getConnection();

//        double temp = 37.5;
//        int id = 1;
//
//        String sql = "UPDATE Vitals SET temperature = " + temp + " WHERE p_id = " + id;
//
//        Statement stmt = conn.createStatement();
//        stmt.executeUpdate(sql);
//
//        //  LỖI: Locale VN có thể thành 37,5 → SQL sai syntax
        double temp = 37.5;
        int id = 1;

        String sql = "UPDATE Vitals SET temperature = ? WHERE p_id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setDouble(1, temp);
        ps.setInt(2, id);

        ps.executeUpdate();

        //  FIX: JDBC tự handle kiểu dữ liệu → không lỗi dấu phẩy
    }
}