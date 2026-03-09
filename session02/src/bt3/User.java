package bt3;

public class User implements Authenticatable {

    private String password;

    public User(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}