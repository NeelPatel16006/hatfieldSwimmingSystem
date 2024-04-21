import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.example.Lesson;
import org.example.Learner;
import org.example.Coach;
public class LessonTest {
    @Test
    void testBookLesson() {
        Lesson lesson = new Lesson("Monday", "4-5pm", 4, new Coach("Mr. Parth"), 1);
        Learner learner = new Learner(12345, "Alice", "Female", 4, "123-456-7890", 1);
        lesson.bookLesson(learner);
        assertTrue(lesson.getLearners().contains(learner));
    }
}
