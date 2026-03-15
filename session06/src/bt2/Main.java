package bt2;



public class Main {

    public static void main(String[] args) {

        TicketPool roomA = new TicketPool("A");
        TicketPool roomB = new TicketPool("B");

        roomA.addTickets(10);
        roomB.addTickets(10);

        Thread c1 = new Thread(new BookingCounter("Quầy1", roomA, roomB));
        Thread c2 = new Thread(new BookingCounter("Quầy2", roomA, roomB));

        Thread supplier = new Thread(new TicketSupplier(roomA, roomB));

        c1.start();
        c2.start();
        supplier.start();
    }
}