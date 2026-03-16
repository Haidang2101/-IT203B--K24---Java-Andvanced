package bt4;

public class OrderService {

    private OrderRepository repo;
    private NotificationService notify;

    public OrderService(OrderRepository repo, NotificationService notify) {
        this.repo = repo;
        this.notify = notify;
    }

    public void createOrder(String id){
        repo.save(id);
        notify.send("Don hang "+id+" da tao");
    }
}