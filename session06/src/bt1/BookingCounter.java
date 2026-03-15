package bt1;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool roomA;
    private TicketPool roomB;
    private int sold = 0;

    public BookingCounter(String name, TicketPool roomA, TicketPool roomB) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    public int getSold() {
        return sold;
    }

    public void run() {

        Random r = new Random();

        while (!roomA.isSoldOut() || !roomB.isSoldOut()) {

            Ticket t;

            if (r.nextBoolean()) {
                t = roomA.sellTicket();
            } else {
                t = roomB.sellTicket();
            }

            if (t != null) {
                sold++;
                System.out.println(name + " bán " + t.getTicketId());
            }
        }
    }
}