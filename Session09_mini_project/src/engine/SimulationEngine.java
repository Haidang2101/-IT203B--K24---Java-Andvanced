package engine;

import factory.VehicleFactory;
import trafficlight.TrafficLight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulationEngine {

    public void start() {

        // Tạo đối tượng đèn giao thông
        // Đây là "trung tâm điều khiển tín hiệu"
        TrafficLight light = new TrafficLight();

        // Tạo ngã tư
        // Đây là vùng nguy hiểm (critical section) - nơi cần đồng bộ
        Intersection intersection = new Intersection();

        // Tạo thread riêng cho đèn giao thông
        // Vì đèn phải tự động đổi trạng thái liên tục
        Thread lightThread = new Thread(light);

        // setDaemon(true):
        // - Thread này là thread nền
        // - Khi chương trình chính kết thúc → thread này cũng tự dừng
        lightThread.setDaemon(true);

        // Bắt đầu chạy đèn (gọi tới method run() của TrafficLight)
        lightThread.start();


        // Tạo Thread Pool để quản lý nhiều xe cùng lúc
        // - Tối đa 10 thread chạy song song
        // - Nếu >10 xe → sẽ xếp hàng chờ
        ExecutorService executor = Executors.newFixedThreadPool(10);


        // Sinh ra 20 phương tiện
        for (int i = 0; i < 20; i++) {

            // Factory tạo xe
            // - Có thể là xe thường hoặc xe ưu tiên (xe cứu thương)
            // - Truyền vào:
            //   + id: định danh xe
            //   + light: để xe biết trạng thái đèn
            //   + intersection: để xe xin quyền đi qua ngã tư
            Runnable vehicle = VehicleFactory.createVehicle(i, light, intersection);

            // Đưa xe vào thread pool
            // → Mỗi xe sẽ chạy trên 1 thread riêng (gọi run())
            executor.execute(vehicle);
        }

        // Nếu muốn mô phỏng thật:
        // thay for bằng while(true) + sleep

        /*
        int id = 0;
        while (true) {
            try {
                executor.execute(
                    VehicleFactory.createVehicle(id++, light, intersection)
                );

                Thread.sleep(1000); // mỗi 1s sinh 1 xe

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        */


        // Lưu ý:
        // executor.shutdown();
        // → dùng khi bạn muốn dừng hệ thống
    }
}