package bt1;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        List<Ticket> listA = new ArrayList<>();
        List<Ticket> listB = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            listA.add(new Ticket("A-" + String.format("%03d", i), "A"));
            listB.add(new Ticket("B-" + String.format("%03d", i), "B"));
        }

        TicketPool roomA = new TicketPool("A", listA);
        TicketPool roomB = new TicketPool("B", listB);

        BookingCounter c1 = new BookingCounter("Quầy1", roomA, roomB);
        BookingCounter c2 = new BookingCounter("Quầy2", roomA, roomB);

        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Quầy1 bán: " + c1.getSold());
        System.out.println("Quầy2 bán: " + c2.getSold());
    }
}