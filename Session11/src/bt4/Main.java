package bt4;


import utils.DatabaseConection;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập tên bệnh nhân: ");
        String name = sc.nextLine();

        String sql = "SELECT * FROM Patients WHERE full_name = ?";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString("full_name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}