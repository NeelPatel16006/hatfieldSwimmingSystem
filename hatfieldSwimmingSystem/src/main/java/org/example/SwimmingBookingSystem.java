package org.example;

import java.util.*;

/**
 * Manages the all the operations and user interactions for the Swimming System for HJSS.
 * Handles learners, coaches, lessons, bookings, Reviews, and generating reports for Learners and Coaches.
 */
public class SwimmingBookingSystem {

    private final List<Coach> coaches = new ArrayList<>();
    private final List<Learner> learners = new ArrayList<>();
    private final List<Lesson> lessons = new ArrayList<>();

    private  int nextLessonId;
    private final Scanner scanner = new Scanner(System.in);

    public SwimmingBookingSystem(){
        this.nextLessonId = 1;
        initializeCoaches();
        initializeLessons();
        initializeLearners();
    }

    /**
     * Initializes predefined coaches.
     */
    private void initializeCoaches() {
        coaches.add(new Coach("Mr.Parth"));
        coaches.add(new Coach("Mr. John"));
        coaches.add(new Coach("Mrs. Lessely"));
    }

    /**
     * Initializes predefined lessons with coaches and grade levels.
     */
    private void initializeLessons() {
        String[] days = {"Monday", "Wednesday", "Friday", "Saturday"};
        String[][] timeSlots = {{"4-5pm", "5-6pm", "6-7pm"}, {"2-3pm", "3-4pm"}};
        for (int gradeLevel = 1; gradeLevel <= 5; gradeLevel++) {
            for (String day : days) {
                String[] slots = day.equals("Saturday") ? timeSlots[1] : timeSlots[0];
                for (String slot : slots) {
                    lessons.add(new Lesson(day, slot, gradeLevel, coaches.get(gradeLevel % coaches.size()),nextLessonId));
                    nextLessonId++;
                }
            }
        }
    }

    /**
     * Initializes pre-registered learners.
     */
    private void initializeLearners() {
        learners.add(new Learner(12345, "Alice", "Female", 4, "123-456-7890", 1));
        learners.add(new Learner(67890, "Zeel", "Female", 5, "123-456-7890", 2));
        learners.add(new Learner(12365, "Keyur", "Male", 6, "456-789-0123", 3));
        learners.add(new Learner(21215, "Monika", "Female", 7, "123-456-7890", 4));
        learners.add(new Learner(67478, "Hina", "Female", 8, "123-446-7890", 5));
        learners.add(new Learner(12252, "Neel", "Male", 9, "440-456-7890", 1));
        learners.add(new Learner(12347, "Reena", "Female", 10, "123-456-7894", 2));
        learners.add(new Learner(54545, "Yatri", "Female", 11, "330-456-7890", 3));
        learners.add(new Learner(36363, "Darshan", "Male", 4, "454-456-7890", 4));
        learners.add(new Learner(47478, "Umang", "Male", 5, "474-456-7890", 5));
        learners.add(new Learner(40404, "Tirth", "Male", 6, "145-456-7890", 1));
        learners.add(new Learner(40212, "Dhrumin", "Male", 7, "848-456-7890", 2));
        learners.add(new Learner(30254, "Shani", "Male", 8, "989-456-7890", 3));
        learners.add(new Learner(98878, "Meet", "Male", 9, "564-456-7890", 4));
        learners.add(new Learner(74567, "Aarti", "Female", 10, "789-456-7890", 5));

    }

    /**
     * Display the main menu and processes user can select what they want to do.
     */
    private void mainMenuofSystem(){
        while(true){
            System.out.println("\n---  Welcome to the Hatfield Junior Swimming School System ---");
            System.out.println("Select an option:");
            System.out.println("1. Register a new learner");
            System.out.println("2. Book a swimming lesson");
            System.out.println("3. change or cancle lesson");
            System.out.println("4. Attend a swimming lesson");
            System.out.println("5. Monthly learner report");
            System.out.println("6. Monthly coach report");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1 -> registerNewLearner();
                case 2 -> bookLesson();
                case 3 -> changeOrCancleBooking();
                case 4 -> attendLesson();
                case 5 -> generatelearnerReport();
                case 6 -> generateCoachReport();
                case 7 ->{
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /**
     * Register a new learner to the system.
     */
    private void registerNewLearner(){
        System.out.println("Enter learner's name:");
        String name = scanner.nextLine();
        System.out.print(" Enter Gender (M/F): ");
        String genderInput = scanner.nextLine();
        String gender;
        if (genderInput.equalsIgnoreCase("M")) {
            gender = "Male";
        } else if (genderInput.equalsIgnoreCase("F")) {
            gender = "Female";
        } else {
            System.out.println("Invalid gender input. Defaulting to 'Male'.");
            gender = "Male"; // Default to 'Male' if invalid input
        }
        System.out.println("Enter age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter emergency contact number:");
        String contact = scanner.nextLine();
        System.out.println("Enter current grade level (1-5):");
        int gradeLevel = scanner.nextInt();
        scanner.nextLine();
        int learnerId = Learner.generateLearnerId();
        learners.add(new Learner(learnerId,name,gender,age,contact,gradeLevel));
        System.out.println( name+" You are registerd successfully. Your learnerId is: "+ learnerId);
        System.out.println("Use Your Learner ID while booking lessons");
    }




    /**
     * Displays the timetable by Day.
     */
    public void displayTimetableByDay(String day) {
        System.out.println("Timetable for " + day + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getDay().equalsIgnoreCase(day)) {
                System.out.println("-".repeat(70));
                System.out.println("Time: " + lesson.getTimeSlot() + ", Grade Level: " + lesson.getGradeLevel() +
                        ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() +
                        ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Displays the timetable by GradeLevel.
     */
    public void displayTimetableByGrade(int gradeLevel) {
        System.out.println("Timetable for Grade " + gradeLevel + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getGradeLevel() == gradeLevel) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() +
                        ", Coach: " + lesson.getCoach().getName() + ", Lesson ID: " + lesson.getLessonID() +
                        ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Displays the timetable by Coach.
     */
    public void displayTimetableByCoach(String coachName) {
        System.out.println("Timetable for Coach " + coachName + ":");
        for (Lesson lesson : lessons) {
            if (lesson.getCoach().getName().equalsIgnoreCase(coachName)) {
                System.out.println("-".repeat(70));
                System.out.println("Day: " + lesson.getDay() + ", TimeSlot: " + lesson.getTimeSlot() +
                        ", GradeLevel: " + lesson.getGradeLevel() + ", Lesson ID: " + lesson.getLessonID() +
                        ", Vacancy: " + (4 - lesson.getLearners().size()));
            }
        }
    }

    /**
     * Available Days and Timeslots
     */
    public void displayAvaiableDaysAndTimeSlots(){
        System.out.println("Available Days and Times:");
        System.out.println("Monday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Wednesday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Friday: 4-5pm, 5-6pm, 6-7pm");
        System.out.println("Saturday: 2-3pm, 3-4pm");
    }

    /**
     * Available coaches with their ratings
     */
    public void displayAvailableCoaches(){
        System.out.println("Available Coaches:");
        for (Coach coach : coaches) {
            double averageRating = coach.getAverageRating();
            System.out.println(coach.getName()+ " -Rating: " + averageRating);
        }
    }


    /**
     * Books a lesson for a learner, ensuring it matches their grade level and also learner ID.
     */
    private void bookLesson(){
        System.out.println("Select an option:");
        System.out.println("1. Display timetable by day");
        System.out.println("2. Display timetable by grade");
        System.out.println("3. Display timetable by coach");
        System.out.println("Enter Your Choice:");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (option){
            case 1 -> {
                displayAvaiableDaysAndTimeSlots();
                System.out.println("Enter Day: ");
                String day = scanner.nextLine();
                displayTimetableByDay(day);
                break;
            }
            case 2 -> {
                System.out.println("Enter GradeLevel: ");
                int grade = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                displayTimetableByGrade(grade);
                break;
            }
            case 3 -> {
                displayAvailableCoaches();
                System.out.println("Enter Coach's Name: ");
                String coachName = scanner.nextLine();
                displayTimetableByCoach(coachName);
                break;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        System.out.println("Enter Learner ID: ");
        int id = scanner.nextInt();
        Learner learner = learners.stream().filter(l -> l.getLearnerID() == id).findFirst().orElse(null);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }

        System.out.print("Enter lesson ID: ");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the grade level of the lesson
        int lessonGradeLevel = findGradeLevelByLessonId(lessonId);

        if (lessonGradeLevel == -1) {
            System.out.println("Lesson not found.");
            return;
        }

        // Check if the lesson ID is already booked by the learner
        if (learner.getBookedLessons().stream().anyMatch(lesson -> lesson.getLessonID() == lessonId)) {
            System.out.println("You have already booked this lesson. Please choose another lesson.");
            return;
        }

        // Check if the learner's grade level allows booking this lesson
        if (learner.getGradeLevel() == lessonGradeLevel || learner.getGradeLevel() + 1 == lessonGradeLevel) {
            Optional<Lesson> optionalLesson = lessons.stream().filter(l ->l.getLessonID() == lessonId).findFirst();

            if (optionalLesson.isPresent()) {
                Lesson lesson = optionalLesson.get();
                lesson.bookLesson(learner);
                learner.bookLesson(lesson);
                System.out.println("Lesson booked successfully.");
            } else {
                System.out.println("Failed to book the lesson. Please check the details and try again.");
            }
        } else {
            System.out.println("You are not allowed to book this lesson based on your grade level.");
        }

    }

    /**
     * Change or cancle lessons a previously booked lesson for a learner.
     */

    private void changeOrCancleBooking(){

        System.out.println("Sellect an Option: ");
        System.out.println("1. Change the lesson");
        System.out.println("2. Cancel the Lesson: ");
        System.out.println("Enter Your Choice: ");

        int opt = scanner.nextInt();
        scanner.nextLine(); // consume new line

        switch (opt){
            case 1 -> changeBooking();
            case 2 -> cancleBooking();
            default -> System.out.println("Invalid option. Please try again.");
        }

    }


    /**
     * Change the  lessons a previously booked lesson for a learner.
     */

    private void changeBooking(){
        System.out.println("Enter learner ID for change or cancle the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerById(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        displayBookedLessons(learnerId);

        // Prompt for lesson ID to  change
        System.out.println("Enter the lesson ID you want to change or change:");
        int oldLessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> oldLesson = findLessonById(oldLessonId);

        if (oldLesson.isPresent()) {
            System.out.println("Select an option:");
            System.out.println("1. Display timetable by day");
            System.out.println("2. Display timetable by grade");
            System.out.println("3. Display timetable by coach");
            System.out.println("Enter Your Choice:");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (option){
                case 1 -> {
                    displayAvaiableDaysAndTimeSlots();
                    System.out.println("Enter Day: ");
                    String day = scanner.nextLine();
                    displayTimetableByDay(day);
                    break;
                }
                case 2 -> {
                    System.out.println("Enter GradeLevel: ");
                    int grade = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    displayTimetableByGrade(grade);
                    break;
                }
                case 3 -> {
                    displayAvailableCoaches();
                    System.out.println("Enter Coach's Name: ");
                    String coachName = scanner.nextLine();
                    displayTimetableByCoach(coachName);
                    break;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            // Prompt for new lesson ID
            System.out.println("Enter the new lesson ID:");
            int newLessonId = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            // Find the new lesson
            Optional<Lesson> newLesson = findLessonById(newLessonId);

            if (newLesson.isPresent()) {
                // change the booking
                learner.changeBooking(oldLesson.get(), newLesson.get());
                System.out.println("Booking changed successfully.");
            } else {
                System.out.println("New lesson not found.");
            }
        } else {
            System.out.println("Old lesson not found.");
        }


    }

    /**
     * Cancel the  lessons a previously booked lesson for a learner.
     */

    private void cancleBooking(){
        System.out.println("Enter learner ID for change or cancle the booking: ");
        int learnerId = scanner.nextInt();

        Learner learner = findLearnerById(learnerId);


        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        displayBookedLessons(learnerId);

        // Prompt for lesson ID to cancel
        System.out.println("Enter the lesson ID you want to cancel:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToCancel = findLessonById(lessonId);

        if (lessonToCancel.isPresent()) {
            learner.cancelLesson(lessonToCancel.get());
            System.out.println("Booking canceled successfully.");
        } else {
            System.out.println("Lesson not found.");
        }


    }

    /**
     * Attend the previously booked lessons for a learner.
     */

    private void attendLesson(){
        System.out.println("Enter learner ID for attending the lesson: ");
        int learnerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Learner learner = findLearnerById(learnerId);

        if (learner == null) {
            System.out.println("Learner not found.");
            return;
        }
        // Display the learner's booked lessons
        displayBookedLessons(learnerId);

        // Prompt for lesson ID to attend
        System.out.println("Enter the lesson ID you want to attend:");
        int lessonId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Find the lesson
        Optional<Lesson> lessonToAttend = findLessonById(lessonId);

        if (lessonToAttend.isPresent()){
            // Attend the lesson
            learner.attendLesson(lessonToAttend.get());
            System.out.println("Lesson attended successfully.");

            System.out.println("Enter your rating (1 to 5): ");
            int rating = scanner.nextInt();
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Rating must be between 1 and 5.");
                return;
            }

            scanner.nextLine(); // Consume newline
            System.out.println("Enter your review about lesson you have attended:");
            String review = scanner.nextLine();

            learner.addReviewForLesson(lessonToAttend.get(), review);
            learner.addRatingForLesson(lessonToAttend.get(), rating);
            System.out.println("Feedback and rating submitted. Thank you!");
            // Pass rating directly to coach
            lessonToAttend.get().getCoach().addRating(rating);

        }

    }

    /**
     * find the learner  information by their learner id
     * @param learnerId
     */
    private Learner findLearnerById(int learnerId) {
        return learners.stream()
                .filter(learner -> learner.getLearnerID() == learnerId)
                .findFirst()
                .orElse(null);
    }

    /**
     * find the lesson information by lesson id
     * @param lessonId
     */
    private Optional<Lesson> findLessonById(int lessonId) {
        return lessons.stream()
                .filter(lesson -> lesson.getLessonID() == lessonId)
                .findFirst();
    }

    /**
     * Display the booked lesson information by learner using learner id
     * @param learnerId
     */
    private void displayBookedLessons(int learnerId) {
        Learner learner = findLearnerById(learnerId);
        if (learner != null) {
            System.out.println("Booked Lessons for Learner with ID " + learnerId + ":");
            for (Lesson lesson : learner.getBookedLessons()) {
                System.out.println("Lesson ID: " + lesson.getLessonID());
                System.out.println("Day: " + lesson.getDay());
                System.out.println("Time: " + lesson.getTimeSlot());
                System.out.println("Coach: " + lesson.getCoach().getName());
                System.out.println("Grade Level: " + lesson.getGradeLevel());
                System.out.println("------------------------");
            }
        } else {
            System.out.println("Learner not found.");
        }
    }

    /**
     * Generates reports about learners activities with booked, cancelled and attended lessons.
     */
    private void generatelearnerReport(){
        for (Learner learner : learners) {
            System.out.println("Learner Name: " + learner.getName());
            System.out.println("Total Booked Lessons: " + learner.getTotalBookedLessons());
            System.out.println("Total Cancelled Lessons: " + learner.getTotalCancelledLessons());
            System.out.println("Total Attended Lessons: " + learner.getTotalAttendedLessons());
            System.out.println("Booked Lessons Details:");
            printLessonDetails(learner.getBookedLessons());
            System.out.println("-".repeat(60));
            System.out.println("Cancelled Lessons Details:");
            printLessonDetails(learner.getCancelledLessons());
            System.out.println("-".repeat(60));

            System.out.println("Attended Lessons Details:");
            printLessonDetails(learner.getAttendedLessons());
            System.out.println("-".repeat(60));

        }
    }

    /**
     * print the details of lessons.
     */
    private void printLessonDetails(List<Lesson> lessons) {
        for (Lesson lesson : lessons) {
            System.out.println("Lesson ID: " + lesson.getLessonID());
            System.out.println("Time Slot: " + lesson.getTimeSlot());
            System.out.println("Grade Level: " + lesson.getGradeLevel());
            System.out.println("Coach: " + lesson.getCoach().getName());
            System.out.println("------------------------");
        }
    }
    /**
     * Generates report about  each coaches with their names and respective ratings.
     */
    private void generateCoachReport(){
        for (Coach coach : coaches) {
            System.out.println("Coach Name: " + coach.getName());
            System.out.println("Average Rating: " + coach.getAverageRating());
            System.out.println("------------------------");
        }
    }

    /**
     * Finds the grade level of a lesson by its ID.
     * @param lessonId The ID of the lesson to find the grade level for.
     * @return The grade level of the lesson with the given ID, or -1 if the lesson is not found.
     */
    private int findGradeLevelByLessonId(int lessonId) {
        Optional<Lesson> optionalLesson = lessons.stream()
                .filter(lesson -> lesson.getLessonID() == lessonId)
                .findFirst();

        return optionalLesson.map(Lesson::getGradeLevel).orElse(-1);
    }


    public static void main(String[] args) {
          SwimmingBookingSystem system = new SwimmingBookingSystem();
          system.mainMenuofSystem();
    }
}