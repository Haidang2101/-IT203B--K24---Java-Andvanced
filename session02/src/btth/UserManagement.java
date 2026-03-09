package btth;

import java.util.function.*;

public class UserManagement {

    public Supplier<User> userSupplier = User::new;

    public Predicate<User> activeChecker = u -> "ACTIVE".equals(u.getStatus());

    public Function<User, String> emailExtractor = User::getEmail;
}