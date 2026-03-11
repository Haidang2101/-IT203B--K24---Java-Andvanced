package bt1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserValidatorTest {

    @Test
    public void TC01_ValidUsername() {
        // Arrange
        UserValidator validator = new UserValidator();
        String username = "user123";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        Assertions.assertTrue(result, "Username 'user123' should be valid");
    }

    @Test
    public void TC02_UsernameTooShort() {
        // Arrange
        UserValidator validator = new UserValidator();
        String username = "abc";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        Assertions.assertFalse(result, "Username 'abc' should be invalid (too short)");
    }

    @Test
    public void TC03_UsernameContainsWhitespace() {
        // Arrange
        UserValidator validator = new UserValidator();
        String username = "user name";

        // Act
        boolean result = validator.isValidUsername(username);

        // Assert
        Assertions.assertFalse(result, "Username 'user name' should be invalid (contains whitespace)");
    }
}
