package btth.presentation;

import btth.entity.Appointment;
import btth.persistence.AppointmentRepository;

import java.sql.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        AppointmentRepository repo = new AppointmentRepository();

        // ADD
        Appointment ap1 = new Appointment(
                "Nguyen Van A",
                Date.valueOf("2026-03-25"),
                "Dr. Strange",
                "Pending"
        );
        repo.addAppointment(ap1);

        // GET ALL
        System.out.println("Danh sách:");
        List<Appointment> list = repo.getAppointments();
        for (Appointment a : list) {
            System.out.println(a.getId() + " | " + a.getPatientName());
        }

        // GET BY ID
        System.out.println("\nChi tiết ID = 1:");
        Appointment ap = repo.getAppointment(1);
        if (ap != null) {
            System.out.println(ap.getPatientName());
        }

        // UPDATE
        Appointment update = new Appointment(
                1,
                "Nguyen Van A Updated",
                Date.valueOf("2026-03-26"),
                "Dr. House",
                "Done"
        );
        repo.updateAppointment(update);

        // DELETE
        repo.deleteAppointment(1);
    }
}