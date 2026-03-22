package bt3;


import utils.DatabaseConection;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập bed_id: ");
        int id = sc.nextInt();

        String sql = "UPDATE Beds SET bed_status = 'Occupied' WHERE bed_id = ?";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();

            if (rows == 0) {
                System.out.println("Không tồn tại mã giường!");
            } else {
                System.out.println("Cập nhật thành công!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}