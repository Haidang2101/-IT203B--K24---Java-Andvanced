// File: Main.java
package bt2;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            // Tắt auto-commit
            conn.setAutoCommit(false);

            String sqlUpdateWallet =
                    "UPDATE Patient_Wallet SET balance = balance - ? WHERE patient_id = ?";

            String sqlUpdateInvoice =
                    "UPDATE Invoices SET status = 'PAID' WHERE invoice_id = ?";

            int patientId = 1;
            int invoiceId = 10;
            int amount = 500000;

            // ================== CODE LỖI (CHƯA ROLLBACK) ==================
            /*
            try (PreparedStatement ps1 = conn.prepareStatement(sqlUpdateWallet)) {
                ps1.setInt(1, amount);
                ps1.setInt(2, patientId);
                ps1.executeUpdate();
            }

            // Giả sử ở đây truy vấn sai tên bảng / cột -> SQLException
            try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice)) {
                ps2.setInt(1, invoiceId);
                ps2.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            // LỖI CỦA LẬP TRÌNH VIÊN: chỉ in ra lỗi, KHÔNG rollback()
            System.out.println("Error: " + e.getMessage());
        }
            */

            // ================== CODE ĐÃ SỬA ĐÚNG ==================

            try (PreparedStatement ps1 = conn.prepareStatement(sqlUpdateWallet)) {
                ps1.setInt(1, amount);
                ps1.setInt(2, patientId);
                ps1.executeUpdate();
            }

            try (PreparedStatement ps2 = conn.prepareStatement(sqlUpdateInvoice)) {
                ps2.setInt(1, invoiceId);
                ps2.executeUpdate();
            }

            // Nếu đến đây không lỗi -> commit
            conn.commit();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());

            // HÀNH ĐỘNG THIẾT YẾU BỊ BỎ QUÊN: rollback transaction
            try {
                if (conn != null) {
                    conn.rollback();  // đưa DB về trạng thái an toàn trước khi lỗi
                }
            } catch (SQLException rollbackEx) {
                // Bọc rollback trong try-catch riêng như đề yêu cầu
                System.out.println("Rollback error: " + rollbackEx.getMessage());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(true); // trả về mặc định
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}