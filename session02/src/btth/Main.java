package btth;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        UserManagement management = new UserManagement();

        User u = management.userSupplier.get();

        System.out.println(IUserAccount.isStandardLength("nguyenvana"));

        List<User> users = new ArrayList<>();

        users.add(new User("haidang2101", "dang@gmail.com", "ADMIN", "ACTIVE"));
        users.add(new User("hieu123", "hieu@gmail.com", "USER", "ACTIVE"));
        users.add(new User("quang456", "quang@gmail.com", "USER", "INACTIVE"));
        users.add(new User("ky567", "Hongky@gmail.com", "ADMIN", "ACTIVE"));

        System.out.println(management.emailExtractor.apply(users.get(0)));

        users.forEach(System.out::println);
    }
}