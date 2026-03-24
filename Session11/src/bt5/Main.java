package bt5;


import utils.DatabaseConection;

import java.sql.*;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Xem bác sĩ");
            System.out.println("2. Thêm bác sĩ");
            System.out.println("3. Thống kê");
            System.out.println("4. Thoát");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: showDoctors(); break;
                case 2: addDoctor(); break;
                case 3: stats(); break;
                case 4: return;
            }
        }
    }

    static void showDoctors() {
        String sql = "SELECT * FROM Doctors";
        try (Connection conn = DatabaseConection.openConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + " - " + rs.getString("name") + " - " + rs.getString("specialty"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    static void addDoctor() {
        System.out.print("Tên: ");
        String name = sc.nextLine();
        System.out.print("Chuyên khoa: ");
        String sp = sc.nextLine();

        String sql = "INSERT INTO Doctors(name, specialty) VALUES(?, ?)";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, sp);
            ps.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    static void stats() {
        String sql = "SELECT specialty, COUNT(*) as total FROM Doctors GROUP BY specialty";
        try (Connection conn = DatabaseConection.openConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("specialty") + ": " + rs.getInt("total"));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}
