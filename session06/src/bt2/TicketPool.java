package bt2;

import java.util.ArrayList;
import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets = new ArrayList<>();
    private int count = 1;

    public TicketPool(String roomName) {
        this.roomName = roomName;
    }

    public synchronized Ticket sellTicket() {

        for (Ticket t : tickets) {
            if (!t.isSold()) {
                t.setSold(true);
                return t;
            }
        }

        return null;
    }

    public synchronized void addTickets(int n) {

        for (int i = 0; i < n; i++) {
            tickets.add(new Ticket(roomName + "-" + String.format("%03d", count++), roomName));
        }
    }
}