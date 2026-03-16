package bt4;

public class SMSNotification implements NotificationService{
    public void send(String msg){
        System.out.println("SMS: "+msg);
    }
}