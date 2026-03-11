
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestCase {
    @BeforeAll
    public static void notification(){
        System.out.println("Testing notification");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("Before each");
    }
    @Test
    public void test() {
        int result = Calculate.plus(5, 7);
        Assertions.assertEquals(12, result);
    }
    @Test
    public void test2() {
        int result = Calculate.minus(5, 7);
        Assertions.assertEquals(-2, result);
    }

}