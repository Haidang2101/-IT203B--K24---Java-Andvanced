package bt5;

public interface AdminActions {

    default void logActivity(String activity) {
        System.out.println("Admin: " + activity);
    }
}