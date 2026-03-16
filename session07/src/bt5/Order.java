package bt5;

import java.util.List;

public class Order {

    String id;
    List<OrderItem> items;

    public Order(String id,List<OrderItem> items){
        this.id=id;
        this.items=items;
    }

    public double total(){
        return items.stream().mapToDouble(OrderItem::total).sum();
    }
}