package btth;

public class Email implements Notification {
    @Override
    public void notify(String message) {
        System.out.println("Email: "+ message);
    }
}
