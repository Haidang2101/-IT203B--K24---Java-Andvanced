package bt2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testRegistrationAge18_ValidBoundary() {
        // Arrange
        UserService service = new UserService();
        int age = 18;

        // Act
        boolean result = service.checkRegistrationAge(age);

        // Assert
        assertEquals(true, result, "Age 18 should be valid for registration");
    }

    @Test
    public void testRegistrationAge17_TooYoung() {
        // Arrange
        UserService service = new UserService();
        int age = 17;

        // Act
        boolean result = service.checkRegistrationAge(age);

        // Assert
        assertEquals(false, result, "Age 17 should be invalid for registration");
    }

    @Test
    public void testRegistrationAgeNegative1_InvalidData() {
        // Arrange
        UserService service = new UserService();
        int age = -1;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.checkRegistrationAge(age);
        }, "Age -1 should throw IllegalArgumentException");
    }
}
