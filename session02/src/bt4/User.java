package bt4;

public class User {

    private String username;

    public User() {
        this.username = "default";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}