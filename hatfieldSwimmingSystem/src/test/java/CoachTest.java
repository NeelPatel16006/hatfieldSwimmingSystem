
import org.junit.jupiter.api.Test;
import org.example.Coach;
import static org.junit.jupiter.api.Assertions.*;
public class CoachTest {
    @Test
    void testAddRating() {
        Coach coach = new Coach("Mr. John");
        coach.addRating(4);
        assertEquals(4, coach.getAverageRating());
    }
}
