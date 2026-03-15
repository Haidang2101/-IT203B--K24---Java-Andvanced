package bt3;

public class Ticket {

    private String id;
    private String room;

    public Ticket(String id, String room) {
        this.id = id;
        this.room = room;
    }

    public String getId() {
        return id;
    }

    public String getRoom() {
        return room;
    }
}