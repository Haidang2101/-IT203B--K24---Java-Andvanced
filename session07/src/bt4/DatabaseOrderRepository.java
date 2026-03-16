package bt4;

public class DatabaseOrderRepository implements OrderRepository{
    public void save(String orderId){
        System.out.println("Luu database: "+orderId);
    }
}