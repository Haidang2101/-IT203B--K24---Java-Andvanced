package bt4;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {

    private String room;
    private Queue<Ticket> tickets = new LinkedList<>();

    public TicketPool(String room, int n) {

        this.room = room;

        for (int i = 1; i <= n; i++) {
            tickets.add(new Ticket(room + "-" + String.format("%03d", i)));
        }
    }

    public Ticket sellTicket() {
        return tickets.poll();
    }

    public boolean isEmpty() {
        return tickets.isEmpty();
    }

    public String getRoom() {
        return room;
    }
}