package bt4;

public class FileOrderRepository implements OrderRepository{
    public void save(String orderId){
        System.out.println("Luu file: "+orderId);
    }
}