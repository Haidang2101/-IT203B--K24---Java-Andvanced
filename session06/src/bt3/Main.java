package bt3;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A");
        TicketPool roomB = new TicketPool("B");

        roomA.addTickets(5);
        roomB.addTickets(5);

        new Thread(new BookingCounter("Quầy1", roomA, roomB)).start();
        new Thread(new BookingCounter("Quầy2", roomA, roomB)).start();

        new Thread(new TicketSupplier(roomA, roomB)).start();
    }
}