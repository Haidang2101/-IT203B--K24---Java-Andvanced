package bt3;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
                new User("alexander","alex@gmail.com"),
                new User("bob","bob@gmail.com"),
                new User("charlotte","charlotte@gmail.com"),
                new User("david","david@gmail.com")
        );

        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();

        users.stream()
                .filter(u -> u.username().contains(key))
                .forEach(System.out::println);
    }
}