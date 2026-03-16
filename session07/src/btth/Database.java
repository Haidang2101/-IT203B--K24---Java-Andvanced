package btth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Database {
    List<Order> orders= new ArrayList<>();
    void save(Order order) {
        orders.add(order);
    }
}
