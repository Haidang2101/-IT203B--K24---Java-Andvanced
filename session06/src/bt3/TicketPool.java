package bt3;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String room;
    private Queue<Ticket> tickets = new LinkedList<>();
    private int count = 1;

    public TicketPool(String room) {
        this.room = room;
    }

    public synchronized Ticket sellTicket() {

        while (tickets.isEmpty()) {
            try {
                System.out.println("Hết vé phòng " + room + ", đang chờ...");
                wait();
            } catch (Exception e) {
            }
        }

        return tickets.poll();
    }

    public synchronized void addTickets(int n) {

        for (int i = 0; i < n; i++) {
            tickets.add(new Ticket(room + "-" + String.format("%03d", count++), room));
        }

        System.out.println("Nhà cung cấp thêm " + n + " vé phòng " + room);

        notifyAll();
    }
}