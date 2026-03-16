package btth;

public class Main {
    static void main(String[] args) {
        public class OrderProcessor {

            public void processOrder(Order order, String paymentType) {
                // 1. Lưu vào Database
                Database db = new Database();
                db.save(order);

                // 2. Xử lý thanh toán
                if (paymentType.equals("CreditCard")) {
                    System.out.println("Đang kết nối API Ngân hàng... Thanh toán Thẻ tín dụng.");
                } else if (paymentType.equals("PayPal")) {
                    System.out.println("Đang mở cổng PayPal... Thanh toán PayPal.");
                } else {
                    throw new IllegalArgumentException("Không hỗ trợ phương thức thanh toán này");
                }

                // 3. Gửi thông báo
                EmailSender emailSender = new EmailSender();
                emailSender.sendEmail(order.getCustomerEmail(), "Đơn hàng của bạn đã được xử lý thành công!");
            }
        }

// Các class phụ trợ hiện tại
        class Order {
            private String customerEmail;
            public String getCustomerEmail() { return customerEmail; }
        }

        class Database {
            public void save(Order order) { System.out.println("Đã lưu đơn hàng vào DB."); }
        }

        class EmailSender {
            public void sendEmail(String email, String message) { System.out.println("Đã gửi Email tới: " + email); }
        }


    }
}
