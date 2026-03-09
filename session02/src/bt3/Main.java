package bt3;

public class Main {
    public static void main(String[] args) {

        User u = new User("123456");

        System.out.println(u.isAuthenticated());

        System.out.println(Authenticatable.encrypt(u.getPassword()));
    }
}