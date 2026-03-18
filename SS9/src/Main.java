import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDatabase db = ProductDatabase.getInstance();

        while (true) {
            System.out.println("\n-------- QUẢN LÝ SẢN PHẨM --------");
            System.out.println("1. Thêm mới sản phẩm");
            System.out.println("2. Xem danh sách sản phẩm");
            System.out.println("3. Cập nhật thông tin sản phẩm");
            System.out.println("4. Xoá sản phẩm");
            System.out.println("5. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 1) {
                System.out.print("Loại sản phẩm (1:sản phẩm Vật lý, 2: sản phẩm Số): ");
                int type = Integer.parseInt(sc.nextLine());
                System.out.print("ID: "); String id = sc.nextLine();
                System.out.print("Tên: "); String name = sc.nextLine();
                System.out.print("Giá: "); double price = Double.parseDouble(sc.nextLine());
                System.out.print(type == 1 ? "Trọng lượng: " : "Dung lượng: ");
                double extra = Double.parseDouble(sc.nextLine());

                Product p = ProductFactory.createProduct(type, id, name, price, extra);
                db.add(p);
                System.out.println("Thêm thành công!");

            } else if (choice == 2) {
                System.out.println("--- DANH SÁCH SẢN PHẨM ---");
                for (Product p : db.getAll()) p.displayInfo();

            } else if (choice == 3) {
                System.out.print("Nhập ID cần cập nhật: ");
                String id = sc.nextLine();
                Product p = db.findById(id);
                if (p != null) {
                    System.out.print("Tên mới: "); p.name = sc.nextLine();
                    System.out.print("Giá mới: "); p.price = Double.parseDouble(sc.nextLine());
                    if (p instanceof PhysicalProduct) {
                        System.out.print("Trọng lượng mới: ");
                        ((PhysicalProduct) p).weight = Double.parseDouble(sc.nextLine());
                    } else if (p instanceof DigitalProduct) {
                        System.out.print("Dung lượng mới: ");
                        ((DigitalProduct) p).size = Double.parseDouble(sc.nextLine());
                    }
                    System.out.println("Cập nhật thành công!");
                } else {
                    System.out.println("Không tìm thấy sản phẩm!");
                }

            } else if (choice == 4) {
                System.out.print("Nhập ID cần xoá: ");
                String id = sc.nextLine();
                db.delete(id);

            } else if (choice == 5) {
                System.out.println("Đang thoát chương trình...");
                break;
            }
        }
        sc.close();
    }
}