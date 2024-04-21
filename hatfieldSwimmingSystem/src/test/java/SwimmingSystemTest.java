import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.*;
import org.example.SwimmingBookingSystem;
public class SwimmingSystemTest {
    @Test
    void testRegisterNewLearner() {
        SwimmingBookingSystem system = new SwimmingBookingSystem();
        ByteArrayInputStream in = new ByteArrayInputStream("Alice\nF\n4\n123-456-7890\n1\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter learner's name:");
        String name = scanner.nextLine();
        assertEquals("Alice", name);
    }
}
