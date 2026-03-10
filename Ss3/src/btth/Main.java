package btth;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<User> users = List.of(
                new User("U01","a@gmail.com","123",true, LocalDate.of(2020,1,1)),
                new User("U02","b@gmail.com","123",false, LocalDate.of(2023,5,10)),
                new User("U03","c@gmail.com","123",true, LocalDate.of(2022,3,12)),
                new User("U04","d@gmail.com","123",true, LocalDate.of(2024,2,1)),
                new User("U05","e@gmail.com","123",false, LocalDate.of(2021,7,5))
        );

        UserService service = new UserService();

        List<User> verifiedUsers = service.getVerifiedUsers(users);

        List<PublicUser> publicUsers = verifiedUsers.stream()
                .map(user -> {
                    long months = Period.between(user.createdAt(), LocalDate.now()).toTotalMonths();
                    Tier tier = service.classifyTier(months);
                    return new PublicUser(user.id(), user.email(), tier);
                })
                .toList();

        System.out.println("""
                ===== USER REPORT =====
                """);

        publicUsers.forEach(System.out::println);
    }
}