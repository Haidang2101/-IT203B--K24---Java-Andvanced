package bt2;

import java.util.Random;

public class BookingCounter implements Runnable {

    private String name;
    private TicketPool roomA;
    private TicketPool roomB;

    public BookingCounter(String name, TicketPool roomA, TicketPool roomB) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
    }

    @Override
    public void run() {

        Random random = new Random();

        while (true) {

            Ticket ticket;

            if (random.nextBoolean()) {
                ticket = roomA.sellTicket();
            } else {
                ticket = roomB.sellTicket();
            }

            if (ticket != null) {
                System.out.println(name + " bán vé " + ticket.getTicketId());
            }

            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}