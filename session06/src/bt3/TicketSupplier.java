package bt3;

public class TicketSupplier implements Runnable {

    private TicketPool roomA;
    private TicketPool roomB;

    public TicketSupplier(TicketPool a, TicketPool b) {
        this.roomA = a;
        this.roomB = b;
    }

    public void run() {

        while (true) {

            try {
                Thread.sleep(3000);
            } catch (Exception e) {
            }

            roomA.addTickets(3);
            roomB.addTickets(3);
        }
    }
}