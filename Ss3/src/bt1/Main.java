package bt1;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander","alex@gmail.com"),
                new User("bob","bob@gmail.com"),
                new User("charlotte","charlotte@gmail.com"),
                new User("david","david@gmail.com"),
                new User("benjamin","ben@gmail.com")
        );

        users.forEach(System.out::println);
    }
}