import dao.OrderDAO;
import dao.OrderDAOImpl;
import dto.OrderItemRequest;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class ConcurrentTest {
    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        CountDownLatch latch = new CountDownLatch(threadCount);
        OrderDAO orderDAO = new OrderDAOImpl();

        for (int i = 0; i < threadCount; i++) {
            new Thread(() -> {
                try {
                    orderDAO.placeOrder(1L, Arrays.asList(new OrderItemRequest(1L, 1)));
                    System.out.println(Thread.currentThread().getName() + " -> SUCCESS");
                } catch (Exception e) {
                    System.out.println(Thread.currentThread().getName() + " -> FAIL: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            }).start();
        }

        latch.await();
        System.out.println("Done.");
    }
}