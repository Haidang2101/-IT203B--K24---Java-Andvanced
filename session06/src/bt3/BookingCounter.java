package bt3;

import java.util.Random;

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

        Random random = new Random();

        while (true) {

            Ticket t;

            if (random.nextBoolean()) {
                t = roomA.sellTicket();
            } else {
                t = roomB.sellTicket();
            }

            System.out.println(name + " bán " + t.getId());

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}