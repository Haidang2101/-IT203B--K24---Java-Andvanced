package btth.persistence;

import btth.entity.Appointment;
import utils.DatabaseConection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentRepository {

    // GET ALL
    public List<Appointment> getAppointments() {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConection.openConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // GET BY ID
    public Appointment getAppointment(int id) {
        String sql = "SELECT * FROM appointments WHERE id = ?";
        Appointment ap = null;

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ap = new Appointment(
                        rs.getInt("id"),
                        rs.getString("patient_name"),
                        rs.getDate("appointment_date"),
                        rs.getString("doctor_name"),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ap;
    }

    // ADD
    public void addAppointment(Appointment ap) {
        String sql = "INSERT INTO appointments(patient_name, appointment_date, doctor_name, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ap.getPatientName());
            ps.setDate(2, ap.getAppointmentDate());
            ps.setString(3, ap.getDoctorName());
            ps.setString(4, ap.getStatus());

            ps.executeUpdate();
            System.out.println("Thêm thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updateAppointment(Appointment ap) {
        String sql = "UPDATE appointments SET patient_name=?, appointment_date=?, doctor_name=?, status=? WHERE id=?";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ap.getPatientName());
            ps.setDate(2, ap.getAppointmentDate());
            ps.setString(3, ap.getDoctorName());
            ps.setString(4, ap.getStatus());
            ps.setInt(5, ap.getId());

            ps.executeUpdate();
            System.out.println("Cập nhật thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteAppointment(int id) {
        String sql = "DELETE FROM appointments WHERE id=?";

        try (Connection conn = DatabaseConection.openConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Xóa thành công!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}