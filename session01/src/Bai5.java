class InvalidAgeException extends Exception {
    public InvalidAgeException(String msg) {
        super(msg);
    }
}

class User {
    private int age;

    public void setAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Tuổi không hợp lệ");
        }
        this.age = age;
    }
}

public class Bai5 {
    public static void main(String[] args) {
        User u = new User();

        try {
            u.setAge(-2);
        } catch (InvalidAgeException e) {
            System.out.println(e.getMessage());
        }
    }
}