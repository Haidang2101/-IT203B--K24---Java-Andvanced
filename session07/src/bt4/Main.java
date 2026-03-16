package bt4;

public class Main {
    public static void main(String[] args) {

        OrderRepository repo = new DatabaseOrderRepository();
        NotificationService notify = new SMSNotification();

        OrderService service = new OrderService(repo,notify);

        service.createOrder("ORD002");
    }
}