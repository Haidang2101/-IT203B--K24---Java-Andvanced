package bt2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander","alex@gmail.com"),
                new User("bob","bob@yahoo.com"),
                new User("charlotte","charlotte@gmail.com"),
                new User("david","david@yahoo.com"),
                new User("benjamin","ben@gmail.com")
        );

        users.stream()
                .filter(u -> u.email().endsWith("@gmail.com"))
                .forEach(System.out::println);
    }
}