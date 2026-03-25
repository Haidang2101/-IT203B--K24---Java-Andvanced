import dao.OrderDAO;
import dao.OrderDAOImpl;
import dto.OrderItemRequest;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAOImpl();

        try {
            long orderId = orderDAO.placeOrder(
                    1L,
                    Arrays.asList(
                            new OrderItemRequest(1L, 2),
                            new OrderItemRequest(2L, 1)
                    )
            );
            System.out.println("Order success. Order ID = " + orderId);
        } catch (Exception e) {
            System.out.println("Order failed: " + e.getMessage());
        }
    }
}