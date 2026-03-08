import java.util.Scanner;

public class Bai1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhập năm sinh: ");
            String input = sc.nextLine();
            int year = Integer.parseInt(input);
            int age = 2026 - year;
            System.out.println("Tuổi: " + age);
        } catch (NumberFormatException e) {
            System.out.println("Dữ liệu không hợp lệ");
        } finally {
            sc.close();
            System.out.println("Thực hiện dọn dẹp tài nguyên trong finally...");
        }
    }
}