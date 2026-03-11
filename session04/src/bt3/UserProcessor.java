package bt3;

import java.util.Locale;

public class UserProcessor {

    public String processEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }

        String normalized = email.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("Email must not be empty");
        }

        int atIndex = normalized.indexOf('@');
        if (atIndex <= 0) {
            throw new IllegalArgumentException("Email must include '@' and a local part");
        }

        String domainPart = normalized.substring(atIndex + 1);
        if (domainPart.isEmpty()) {
            throw new IllegalArgumentException("Email must include a domain");
        }

        return normalized.toLowerCase(Locale.ROOT);
    }
}
