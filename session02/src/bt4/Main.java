package bt4;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {

        List<User> users = Arrays.asList(
                new User("dang"),
                new User("hieu"),
                new User("ky")
        );

        users.stream().map(User::getUsername).forEach(System.out::println);

        Supplier<User> creator = User::new;

        User u = creator.get();
        System.out.println(u.getUsername());
    }
}