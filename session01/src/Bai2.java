import java.util.Scanner;

public class Bai2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập tổng số người: ");
        int total = sc.nextInt();

        System.out.print("Nhập số nhóm: ");
        int group = sc.nextInt();

        try {
            int result = total / group;
            System.out.println("Mỗi nhóm có: " + result + " người");
        } catch (ArithmeticException e) {
            System.out.println("Không thể chia cho 0!");
        }
    }
}