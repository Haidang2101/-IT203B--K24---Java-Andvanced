package bt1;

import java.util.List;

public class TicketPool {

    private String roomName;
    private List<Ticket> tickets;

    public TicketPool(String roomName, List<Ticket> tickets) {
        this.roomName = roomName;
        this.tickets = tickets;
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

    public boolean isSoldOut() {
        return tickets.stream().allMatch(Ticket::isSold);
    }
}