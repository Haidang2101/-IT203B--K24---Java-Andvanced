package bt4;

public class EmailNotification implements NotificationService{
    public void send(String msg){
        System.out.println("Email: "+msg);
    }
}