package bt2;

public class TicketSupplier implements Runnable {

    private TicketPool roomA;
    private TicketPool roomB;

    public TicketSupplier(TicketPool roomA, TicketPool roomB) {
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public void run() {

        while (true) {

            try {
                Thread.sleep(3000);
            } catch (Exception e) {}

            roomA.addTickets(3);
            roomB.addTickets(3);

            System.out.println("Nhà cung đã cấp thêm vé");
        }
    }
}