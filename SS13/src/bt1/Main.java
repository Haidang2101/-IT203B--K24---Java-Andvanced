package bt1;

import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Main {

    public static void main(String[] args) {
        // Giả sử có sẵn hàm getConnection() ở đâu đó trong project
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();

            // ================== CODE LỖI (CỦA JUNIOR) ==================
            // MÔ PHỎNG: Junior nghĩ rằng gọi 2 executeUpdate() liên tiếp
            // thì DB sẽ tự hiểu đó là 1 "cục nghiệp vụ" (1 transaction).
            //
            // VẤN ĐỀ:
            //  - JDBC mặc định auto-commit = true
            //  - Mỗi lệnh executeUpdate() là 1 transaction riêng
            //  - Lệnh 1 (trừ kho) chạy xong, không lỗi -> COMMIT NGAY
            //  - Lệnh 2 (insert lịch sử) nếu bị lỗi -> chỉ FAIL transaction 2
            //    => KHÔNG rollback lại transaction 1
            //
            // KẾT QUẢ:
            //  - Thuốc trong kho đã bị trừ (vì lệnh 1 đã commit)
            //  - Không có bản ghi trong Prescription_History (vì lệnh 2 lỗi)
            //
            // => VI PHẠM TÍNH ATOMICITY (nguyên tử) của Transaction

            /*
            String sqlUpdateInventory = "UPDATE Medicine_Inventory " +
                                        "SET quantity = quantity - 1 " +
                                        "WHERE medicine_id = ?";

            String sqlInsertHistory = "INSERT INTO Prescription_History " +
                                      "(patient_id, doctor_id, medicine_id, quantity, time_issued) " +
                                      "VALUES (?, ?, ?, ?, ?)";

            int medicineId = 1;
            int patientId = 1001;
            int doctorId = 2001;
            int quantity = 1;

            // Lúc này conn vẫn đang auto-commit = true

            PreparedStatement ps1 = conn.prepareStatement(sqlUpdateInventory);
            ps1.setInt(1, medicineId);
            ps1.executeUpdate(); // LỆNH 1: trừ kho -> COMMIT NGAY SAU KHI CHẠY

            PreparedStatement ps2 = conn.prepareStatement(sqlInsertHistory);
            ps2.setInt(1, patientId);
            ps2.setInt(2, doctorId);
            ps2.setInt(3, medicineId);
            ps2.setInt(4, quantity);
            ps2.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            ps2.executeUpdate(); // LỆNH 2: nếu lỗi -> chỉ FAIL transaction 2
            */

            // ================== CODE ĐÚNG (SỬA LẠI) ==================
            // Ý TƯỞNG SỬA:
            //  1. Tắt auto-commit: conn.setAutoCommit(false);
            //  2. Thực hiện 2 lệnh UPDATE + INSERT trong cùng 1 transaction
            //  3. Nếu cả 2 lệnh đều OK -> commit()
            //  4. Nếu bất kỳ lệnh nào lỗi -> rollback()
            //
            // => Đảm bảo tính nguyên tử:
            //    Hoặc trừ kho + ghi lịch sử cùng thành công
            //    Hoặc CẢ HAI CÙNG KHÔNG XẢY RA

            String sqlUpdateInventoryFixed = "UPDATE Medicine_Inventory " +
                    "SET quantity = quantity - ? " +
                    "WHERE medicine_id = ?";

            String sqlInsertHistoryFixed = "INSERT INTO Prescription_History " +
                    "(patient_id, doctor_id, medicine_id, quantity, time_issued) " +
                    "VALUES (?, ?, ?, ?, ?)";

            int medicineIdFixed = 1;
            int patientIdFixed = 1001;
            int doctorIdFixed = 2001;
            int quantityFixed = 1;

            boolean oldAutoCommit = conn.getAutoCommit();
            try {
                // 1. Tắt auto-commit -> từ giờ tự quản lý transaction
                conn.setAutoCommit(false);

                // 2. LỆNH 1: trừ thuốc trong kho
                try (PreparedStatement psInv = conn.prepareStatement(sqlUpdateInventoryFixed)) {
                    psInv.setInt(1, quantityFixed);
                    psInv.setInt(2, medicineIdFixed);

                    int rows1 = psInv.executeUpdate();
                    if (rows1 != 1) {
                        throw new SQLException("Update inventory failed, affected rows = " + rows1);
                    }
                }

                // 3. LỆNH 2: ghi lịch sử cấp phát
                try (PreparedStatement psHis = conn.prepareStatement(sqlInsertHistoryFixed)) {
                    psHis.setInt(1, patientIdFixed);
                    psHis.setInt(2, doctorIdFixed);
                    psHis.setInt(3, medicineIdFixed);
                    psHis.setInt(4, quantityFixed);
                    psHis.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

                    int rows2 = psHis.executeUpdate();
                    if (rows2 != 1) {
                        throw new SQLException("Insert history failed, affected rows = " + rows2);
                    }
                }

                // 4. Nếu đến đây mà không có exception -> commit toàn bộ
                conn.commit();
                System.out.println("Transaction OK: trừ kho + ghi lịch sử thành công.");

            } catch (Exception ex) {
                // Có lỗi ở bất kỳ bước nào -> rollback tất cả thay đổi
                System.err.println("Transaction lỗi, rollback toàn bộ.");
                ex.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException rbEx) {
                    System.err.println("Lỗi khi rollback:");
                    rbEx.printStackTrace();
                }
            } finally {
                // Trả auto-commit về trạng thái cũ cho an toàn
                try {
                    conn.setAutoCommit(oldAutoCommit);
                } catch (SQLException ignore) {
                }
            }

        } catch (Exception outerEx) {
            outerEx.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}