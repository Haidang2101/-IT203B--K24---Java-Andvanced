package entity;

import engine.Intersection;
import trafficlight.TrafficLight;

public abstract class Vehicle implements Runnable {

    // Tên phương tiện
    protected String name;

    // Vị trí hiện tại của xe trên đường
    // Giả lập: khi position >= 10 thì coi như đến ngã tư
    protected int position = 0;

    // Tốc độ di chuyển (mỗi lần move sẽ tăng position lên speed đơn vị)
    protected int speed;

    // Tham chiếu tới đèn giao thông
    // Xe dùng cái này để biết: đang đỏ hay xanh
    protected TrafficLight trafficLight;

    // Tham chiếu tới ngã tư (Intersection)
    // Xe phải xin quyền từ đây để đi qua (do có lock)
    protected Intersection intersection;


    // Constructor: khởi tạo thông tin cho xe
    public Vehicle(String name, int speed, TrafficLight light, Intersection intersection) {
        this.name = name;
        this.speed = speed;
        this.trafficLight = light;
        this.intersection = intersection;
    }


    // Phương thức abstract để xác định xe có phải xe ưu tiên không
    // - StandardVehicle -> return false
    // - PriorityVehicle -> return true
    public abstract boolean isPriority();


    @Override
    public void run() {

        // Vòng lặp vô hạn
        // Mỗi xe sẽ chạy liên tục như ngoài đời
        while (true) {

            // Bước 1: xe di chuyển
            move();

            // Bước 2: kiểm tra xem đã đến ngã tư chưa
            if (position >= 10) {

                // Bước 3: xử lý đèn giao thông
                // Nếu đèn đỏ → xe phải chờ
                // Nếu là xe ưu tiên → có thể bỏ qua
                waitForGreenLight();

                // Bước 4: xin quyền đi qua ngã tư
                // (có thể bị chờ nếu có xe khác đang đi)
                passIntersection();

                // Sau khi đi qua ngã tư xong
                // reset vị trí để mô phỏng xe tiếp tục hành trình mới
                position = 0;
            }

            // Bước 5: nghỉ một chút trước khi lặp tiếp
            // tránh CPU chạy 100%
            sleep();
        }
    }


    // Phương thức di chuyển
    protected void move() {

        // Tăng vị trí theo tốc độ
        position += speed;

        // In log để theo dõi trạng thái xe
        util.Logger.log(name + " -> position: " + position);
    }


    // Phương thức xử lý khi gặp đèn giao thông
    protected void waitForGreenLight() {

        // Nếu là xe ưu tiên (ví dụ: xe cứu thương)
        // → bỏ qua đèn, không cần chờ
        if (isPriority()) {
            util.Logger.log(name + " là xe ưu tiên, bỏ qua đèn");
            return;
        }

        // Nếu là xe thường:
        // Khi đèn đỏ → phải chờ
        // Dùng while để liên tục kiểm tra trạng thái đèn
        while (trafficLight.isRed()) {

            // Log trạng thái đang chờ
            util.Logger.log(name + " đang chờ đèn đỏ");

            try {
                // Cho thread ngủ 1 chút trước khi kiểm tra lại
                // tránh vòng lặp chạy quá nhanh gây tốn CPU
                Thread.sleep(500);

            } catch (InterruptedException e) {

                // Nếu thread bị interrupt → khôi phục trạng thái interrupt
                Thread.currentThread().interrupt();
            }
        }

        // Khi thoát khỏi while:
        // → đèn đã chuyển sang xanh
        // → xe được phép đi tiếp
    }


    // Phương thức xin đi qua ngã tư
    protected void passIntersection() {

        try {
            // Gọi tới Intersection
            // Ở đây sẽ có lock để đảm bảo chỉ 1 xe vào tại 1 thời điểm
            intersection.enter(this);

        } catch (Exception e) {

            // Nếu có lỗi (ví dụ:
            // - kẹt xe
            // - va chạm
            // thì log ra để theo dõi)
            util.Logger.log(name + " gặp lỗi: " + e.getMessage());
        }
    }


    // Phương thức sleep giữa các lần di chuyển
    protected void sleep() {

        try {
            // Delay 500ms
            // giúp mô phỏng chuyển động mượt hơn
            Thread.sleep(500);

        } catch (InterruptedException e) {

            // Khôi phục trạng thái interrupt
            Thread.currentThread().interrupt();
        }
    }


    // Getter để lấy tên xe (dùng trong log)
    public String getName() {
        return name;
    }
}