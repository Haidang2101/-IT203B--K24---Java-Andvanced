package bt5;

public class AccessControl {

    public boolean canPerformAction(User user, Action action) {
        if (user == null || action == null) {
            throw new IllegalArgumentException("User and action must not be null");
        }

        Role role = user.getRole();
        switch (role) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action != Action.DELETE_USER;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}
