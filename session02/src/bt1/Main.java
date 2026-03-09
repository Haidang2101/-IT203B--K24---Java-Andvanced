package bt1;

import java.util.function.*;

class User {
    String username;
    boolean admin;

    User(String username, boolean admin) {
        this.username = username;
        this.admin = admin;
    }

    String getUsername() {
        return username;
    }

    boolean isAdmin() {
        return admin;
    }
}

public class Main {
    public static void main(String[] args) {

        Predicate<User> checkAdmin = User::isAdmin;

        Function<User, String> usernameMapper = User::getUsername;

        Consumer<User> printUser = u -> System.out.println(u.getUsername());

        Supplier<User> createUser = () -> new User("defaultUser", false);

        User u = new User("dang", true);

        System.out.println(checkAdmin.test(u));
        System.out.println(usernameMapper.apply(u));
        printUser.accept(u);

        User newUser = createUser.get();
        System.out.println(newUser.getUsername());
    }
}