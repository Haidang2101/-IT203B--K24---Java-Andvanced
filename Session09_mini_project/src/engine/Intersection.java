package engine;

import entity.Vehicle;
import java.util.concurrent.locks.ReentrantLock;

public class Intersection {

    // ReentrantLock dùng để kiểm soát quyền truy cập vào ngã tư
    // true = fairness: các thread sẽ được phục vụ theo thứ tự đến (FIFO)
    // giúp tránh tình trạng một xe bị chờ quá lâu (starvation)
    private final ReentrantLock lock = new ReentrantLock(true);


    // Phương thức để xe xin quyền đi qua ngã tư
    public void enter(Vehicle v) {

        try {
            // lock():
            // - Nếu ngã tư đang trống → xe vào ngay
            // - Nếu đang có xe khác → thread này sẽ chờ
            // Mô phỏng thực tế: xe xếp hàng chờ vào ngã tư
            lock.lock();

            // Xe đã vào được ngã tư
            util.Logger.log(">>> " + v.getName() + " vào ngã tư");

            // Giả lập thời gian xe đi qua ngã tư
            // Trong thời gian này, các xe khác phải chờ
            Thread.sleep(1000);

            // Xe rời khỏi ngã tư
            util.Logger.log("<<< " + v.getName() + " rời ngã tư");

        } catch (InterruptedException e) {

            // Nếu thread bị interrupt, khôi phục lại trạng thái interrupt
            Thread.currentThread().interrupt();

        } finally {

            // Luôn phải unlock dù có lỗi hay không
            // Nếu không unlock, ngã tư sẽ bị khóa vĩnh viễn
            lock.unlock();
        }
    }
}