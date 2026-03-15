package bt4;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool roomA;
    private TicketPool roomB;

    public BookingCounter(String name, TicketPool a, TicketPool b) {
        this.name = name;
        this.roomA = a;
        this.roomB = b;
    }

    public void run() {

        while (true) {

            TicketPool first = roomA;
            TicketPool second = roomB;

            if (roomA.getRoom().compareTo(roomB.getRoom()) > 0) {
                first = roomB;
                second = roomA;
            }

            synchronized (first) {
                synchronized (second) {

                    if (roomA.isEmpty() || roomB.isEmpty())
                        return;

                    Ticket t1 = roomA.sellTicket();
                    Ticket t2 = roomB.sellTicket();

                    System.out.println(name + " bán combo "
                            + t1.getId() + " & " + t2.getId());
                }
            }
        }
    }
}
