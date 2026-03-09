package bt6;

public class Main {
    public static void main(String[] args) {

        UserProcessor processor = UserUtils::convertToUpperCase;

        User u = new User("dang");

        String result = processor.process(u);

        System.out.println(result);
    }
}