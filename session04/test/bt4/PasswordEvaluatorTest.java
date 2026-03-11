package bt4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordEvaluatorTest {

    private PasswordEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new PasswordEvaluator();
    }

    @Test
    void evaluatePasswordStrength_strongPasswordReturnsManh() {
        assertEquals("Mạnh", evaluator.evaluatePasswordStrength("Abc123!@"));
    }

    @Test
    void evaluatePasswordStrength_missingCategoryReturnsTrungBinh() {
        assertAll("medium strength scenarios",
            () -> assertEquals("Trung bình", evaluator.evaluatePasswordStrength("abc123!@"), "missing uppercase"),
            () -> assertEquals("Trung bình", evaluator.evaluatePasswordStrength("ABC123!@"), "missing lowercase"),
            () -> assertEquals("Trung bình", evaluator.evaluatePasswordStrength("Abcdef!@"), "missing digit"),
            () -> assertEquals("Trung bình", evaluator.evaluatePasswordStrength("Abc12345"), "missing special")
        );
    }

    @Test
    void evaluatePasswordStrength_weakInputsReturnYeu() {
        assertAll("weak strength scenarios",
            () -> assertEquals("Yếu", evaluator.evaluatePasswordStrength("Ab1!"), "too short"),
            () -> assertEquals("Yếu", evaluator.evaluatePasswordStrength("password"), "only lowercase letters"),
            () -> assertEquals("Yếu", evaluator.evaluatePasswordStrength("ABC12345"), "only uppercase and digits")
        );
    }
}
