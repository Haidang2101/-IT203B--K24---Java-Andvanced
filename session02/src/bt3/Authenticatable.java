package bt3;

public interface Authenticatable {

    String getPassword();

    default boolean isAuthenticated() {
        return getPassword() != null && !getPassword().isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENC_" + rawPassword;
    }
}