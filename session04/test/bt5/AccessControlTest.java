package bt5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccessControlTest {

    private AccessControl accessControl;
    private User user;

    @BeforeEach
    void setUp() {
        accessControl = new AccessControl();
    }

    @AfterEach
    void tearDown() {
        user = null;
    }

    @Test
    void adminRole_grantsAllPermissionsTopDown() {
        user = new User(Role.ADMIN);
        assertAll("admin permissions",
            () -> assertTrue(accessControl.canPerformAction(user, Action.DELETE_USER), "ADMIN should delete users"),
            () -> assertTrue(accessControl.canPerformAction(user, Action.LOCK_USER), "ADMIN should lock users"),
            () -> assertTrue(accessControl.canPerformAction(user, Action.VIEW_PROFILE), "ADMIN should view profiles")
        );
    }

    @Test
    void moderatorRole_allowsLockingAndViewingOnly() {
        user = new User(Role.MODERATOR);
        assertAll("moderator permissions",
            () -> assertFalse(accessControl.canPerformAction(user, Action.DELETE_USER), "MODERATOR cannot delete users"),
            () -> assertTrue(accessControl.canPerformAction(user, Action.LOCK_USER), "MODERATOR can lock users"),
            () -> assertTrue(accessControl.canPerformAction(user, Action.VIEW_PROFILE), "MODERATOR can view profiles")
        );
    }

    @Test
    void userRole_allowsViewingOnly() {
        user = new User(Role.USER);
        assertAll("user permissions",
            () -> assertFalse(accessControl.canPerformAction(user, Action.DELETE_USER), "USER cannot delete users"),
            () -> assertFalse(accessControl.canPerformAction(user, Action.LOCK_USER), "USER cannot lock users"),
            () -> assertTrue(accessControl.canPerformAction(user, Action.VIEW_PROFILE), "USER can view profiles")
        );
    }
}
