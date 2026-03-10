package bt5;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> users = List.of(
                new User("alexander","a@gmail.com"),
                new User("bob","b@gmail.com"),
                new User("charlotte","c@gmail.com"),
                new User("david","d@gmail.com"),
                new User("benjamin","e@gmail.com")
        );

        users.stream()
                .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
                .limit(3)
                .forEach(u -> System.out.println(u.username()));
    }
}