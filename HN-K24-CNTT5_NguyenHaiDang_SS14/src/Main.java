import utils.DatabaseConnection;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String senderId = "ACC01";
        String receiverId = "ACC02";
        double amount = 1000;

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();

            conn.setAutoCommit(false); // bật transaction


            String checkSql = "SELECT Balance FROM Accounts WHERE AccountId = ?";
            try (PreparedStatement ps = conn.prepareStatement(checkSql)) {

                ps.setString(1, senderId);
                ResultSet rs = ps.executeQuery();

                if (!rs.next()) {
                    throw new Exception("Không tìm thấy tài khoản");
                }

                double balance = rs.getDouble("Balance");

                if (balance < amount) {
                    throw new Exception("Không đủ tiền");
                }
            }

            String callSql = "{CALL sp_UpdateBalance(?, ?)}";

            try (CallableStatement cs = conn.prepareCall(callSql)) {

                // trừ tiền
                cs.setString(1, senderId);
                cs.setDouble(2, -amount);
                cs.execute();

                // cộng tiền
                cs.setString(1, receiverId);
                cs.setDouble(2, amount);
                cs.execute();
            }

            conn.commit();
            System.out.println("Chuyển tiền thành công!");

            String showSql = "SELECT * FROM Accounts WHERE AccountId IN (?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(showSql)) {
                ps.setString(1, senderId);
                ps.setString(2, receiverId);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println(
                            rs.getString("AccountId") + " | " +
                                    rs.getString("FullName") + " | " +
                                    rs.getDouble("Balance")
                    );
                }
            }

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());

            try {
                if (conn != null) {
                    conn.rollback();
                    System.out.println("Rollback thành công!");
                }
            } catch (SQLException ex) {
                System.out.println("Rollback lỗi!");
            }

        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Đóng kết nối lỗi!");
            }
        }
    }
}