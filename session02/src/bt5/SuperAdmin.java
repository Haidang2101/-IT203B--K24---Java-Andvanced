package bt5;

public class SuperAdmin implements UserActions, AdminActions {

    public void logActivity(String activity) {
        UserActions.super.logActivity(activity);
    }
}