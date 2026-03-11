package bt3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserProcessorTest {

    private UserProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new UserProcessor();
    }

    @Test
    void processEmail_validEmailReturnsSameValue() {
        String result = processor.processEmail("user@gmail.com");
        assertEquals("user@gmail.com", result);
    }

    @Test
    void processEmail_missingAtSymbolThrows() {
        assertThrows(IllegalArgumentException.class, () -> processor.processEmail("usergmail.com"));
    }

    @Test
    void processEmail_missingDomainThrows() {
        assertThrows(IllegalArgumentException.class, () -> processor.processEmail("user@"));
    }

    @Test
    void processEmail_mixedCaseEmailIsLowercased() {
        String result = processor.processEmail("Example@Gmail.com");
        assertEquals("example@gmail.com", result);
    }
}
