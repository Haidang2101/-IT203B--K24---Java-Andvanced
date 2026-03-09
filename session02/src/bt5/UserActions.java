package bt5;

public interface UserActions {

    default void logActivity(String activity) {
        System.out.println("User: " + activity);
    }
}