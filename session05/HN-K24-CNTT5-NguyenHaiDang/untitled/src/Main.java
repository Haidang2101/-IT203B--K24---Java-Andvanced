import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ProductManager manager = new ProductManager();

        int choice;

        do {

            System.out.println("\n========= PRODUCT MANAGEMENT SYSTEM =========");
            System.out.println("1. Them san pham moi");
            System.out.println("2. Hien thi danh sach san pham");
            System.out.println("3. Cap nhat so luong theo ID");
            System.out.println("4. Xoa san pham het hang");
            System.out.println("5. Thoat");
            System.out.println("=============================================");
            System.out.print("Lua chon cua ban: ");

            choice = sc.nextInt();

            try {

                switch (choice) {

                    case 1:

                        System.out.print("Nhap ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nhap ten san pham: ");
                        String name = sc.nextLine();

                        System.out.print("Nhap gia san pham: ");
                        double price = sc.nextDouble();

                        System.out.print("Nhap so luong san pham: ");
                        int quantity = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Nhap loai san pham: ");
                        String category = sc.nextLine();

                        Product p = new Product(id, name, price, quantity, category);
                        manager.addProduct(p);

                        break;

                    case 2:
                        manager.displayProducts();
                        break;

                    case 3:

                        System.out.print("Nhap ID: ");
                        int updateId = sc.nextInt();

                        System.out.print("Nhap so luong moi: ");
                        int newQuantity = sc.nextInt();

                        manager.updateQuantity(updateId, newQuantity);

                        break;

                    case 4:
                        manager.deleteOutOfStock();
                        break;

                    case 5:
                        System.out.println("Thoat chuong trinh");
                        break;

                    default:
                        System.out.println("Lua chon khong hop le");

                }

            } catch (InvalidProductException e) {
                System.out.println("Loi: " + e.getMessage());
            }

        } while (choice != 5);

    }
}