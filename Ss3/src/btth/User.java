package btth;

import java.time.LocalDate;

public record User(
        String id,
        String email,
        String password,
        boolean verified,
        LocalDate createdAt
) {
    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", verified=" + verified +
                ", createdAt=" + createdAt +
                '}';
    }
}