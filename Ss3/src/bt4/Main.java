package bt4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander","alex@gmail.com"),
                new User("alexander","alex@gmail.com"),
                new User("charlotte","charlotte@gmail.com"),
                new User("charlotte","charlotte@gmail.com")
        );

        users.stream()
                .distinct()
                .forEach(System.out::println);
    }
}