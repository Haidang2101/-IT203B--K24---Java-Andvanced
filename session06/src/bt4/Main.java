package bt4;

public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A", 10);
        TicketPool roomB = new TicketPool("B", 10);

        new Thread(new BookingCounter("Quầy1", roomA, roomB)).start();
        new Thread(new BookingCounter("Quầy2", roomA, roomB)).start();
    }
}