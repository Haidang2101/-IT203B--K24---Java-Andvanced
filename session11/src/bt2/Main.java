package bt2;


import utils.DatabaseConection;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String sql = "SELECT medicine_name, stock FROM Pharmacy";

        try (Connection conn = DatabaseConection.openConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("medicine_name") + " - " + rs.getInt("stock"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}