package btth;

public class SMS implements Notification {
    @Override
    public void notify(String message) {
        System.out.println("SMS: "+ message);
    }

}
