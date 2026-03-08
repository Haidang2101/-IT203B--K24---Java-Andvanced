import java.io.FileNotFoundException;
import java.util.Scanner;

class InvalidAgeExceptions extends Exception {
    public InvalidAgeExceptions(String message) {
        super(message);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class UserService {

    public static String registerUser(String name, String ageInput, String email)
            throws InvalidAgeException, InvalidEmailException {

        int age;

        try {
            age = Integer.parseInt(ageInput);
        } catch (NumberFormatException e) {
            throw e;
        }

        if (age < 18) {
            throw new InvalidAgeException("Lỗi nghiệp vụ: Tuổi không đủ để đăng ký hệ thống.");
        }

        if (!email.contains("@")) {
            throw new InvalidEmailException("Lỗi nghiệp vụ: Email không hợp lệ.");
        }

        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }

    public static void saveUserToFile(String userData) throws FileNotFoundException {
        throw new FileNotFoundException("Không tìm thấy file lưu trữ.");
    }
}

public class Btth {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.print("Nhập tên: ");
            String name = scanner.nextLine();

            System.out.print("Nhập tuổi: ");
            String age = scanner.nextLine();

            System.out.print("Nhập email: ");
            String email = scanner.nextLine();

            String userData = UserService.registerUser(name, age, email);

            System.out.println("Đăng ký thành công!");
            System.out.println(userData);

            UserService.saveUserToFile(userData);

        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Tuổi phải là một con số!");
        } catch (FileNotFoundException e) {
            System.out.println("Lỗi hệ thống: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Hoàn tất luồng xử lý đăng ký.");
        }
    }
}