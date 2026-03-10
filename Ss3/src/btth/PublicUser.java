package btth;

public record PublicUser(
        String id,
        String email,
        Tier tier
) {}