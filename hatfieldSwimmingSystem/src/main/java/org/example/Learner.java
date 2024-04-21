package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a learner in the Hatfield Junior Swimming School (HJSS).
 * Stores details such as name, gender, age, emergency contact, and grade level.
 * Also manages the learner's feedback on attended lessons.
 */
public class Learner {

    private final int learnerID;
    private final String name;
    private final String gender;
    private int age;
    private final String emergencyContact;
    private int gradeLevel;
    private final Map<Lesson, String> attendedLessonsWithReview;
    private final Map<Lesson, Integer> attendedLessonsWithRatings;
    private List<Lesson> bookedLessons;
    private List<Lesson> cancelledLessons;
    private List<Lesson> attendedLessons;



    /**
     * Constructs a new Learner with the specified details.
     * @param learnerID        the learner's ID
     * @param name             the learner's name
     * @param gender           the learner's gender
     * @param age              the learner's age
     * @param emergencyContact the learner's emergency contact number
     * @param gradeLevel       the learner's current grade level in swimming
     */

    public Learner(int learnerID, String name, String gender, int age, String emergencyContact, int gradeLevel ){
        if (learnerID != 0) {
            this.learnerID = learnerID;
        } else {
            this.learnerID = generateLearnerId();
        }
        this.name = name;
        this.gender = gender;
        this.setAge(age);
        this.emergencyContact = emergencyContact;
        this.setGradeLevel(gradeLevel);
        this.attendedLessonsWithReview = new HashMap<>();
        this.attendedLessonsWithRatings = new HashMap<>();
        this.bookedLessons = new ArrayList<>();
        this.cancelledLessons= new ArrayList<>();
        this.attendedLessons = new ArrayList<>();

    }

    public static int generateLearnerId() {
        // Generate a random 5-digit learner ID
        int learnerId = (int) (Math.random() * 90000) + 10000; // Generate a 5-digit number
        return learnerId;
    }

    /**
     * Sets the age of the learner, ensuring it is between 4 and 11.
     *
     * @param age the learner's age
     */

    public void setAge(int age) {
        if (age >= 4 && age <= 11) {
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be between 4 and 11.");
        }
    }

    /**
     * Sets the grade level of the learner, ensuring it is between 1 and 5.
     *
     * @param gradeLevel the learner's grade level
     */

    public void setGradeLevel(int gradeLevel) {
        if (gradeLevel >= 1 && gradeLevel <= 5) {
            this.gradeLevel = gradeLevel;
        } else {
            throw new IllegalArgumentException("Grade level must be between 1 and 5.");
        }
    }

    /**
     * Records review for an attended lesson.
     *
     * @param lesson  the attended lesson
     * @param review given by learner to lesson
     */

    public void addReviewForLesson(Lesson lesson, String review) {
        this.attendedLessonsWithReview.put(lesson, review);
    }



    /**
     * Records a rating for an attended lesson.
     *
     * @param lesson the attended lesson
     * @param rating the rating given by learner to the lesson
     */
    public void addRatingForLesson(Lesson lesson, int rating) {
        if (rating >= 1 && rating <= 5) {
            this.attendedLessonsWithRatings.put(lesson, rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
    }

    public int getLearnerID(){
        return learnerID;
    }
    public String getName(){
        return name;
    }

    public String getGender() {
        return gender;
    }

    public  int getAge(){
        return age;
    }
    public String getEmergencyContact() {
        return emergencyContact;
    }

    public int getGradeLevel(){
        return gradeLevel;
    }

    /**
     * Updates the learner's grade level to the next level after attending a higher grade lesson.
     */
    public void upgradeGradeLevel() {
        if (this.gradeLevel < 5) { // Ensures the grade level does not exceed 5.
            this.gradeLevel += 1;
        }
    }

    /**
     * Gets a details of attended lessons with their feedback.
     *
     * @return a map of lessons to review strings
     */
    public Map<Lesson, String> getAttendedLessonsWithFeedback() {
        return new HashMap<>(attendedLessonsWithReview);
    }

    /**
     * Gets a map of attended lessons with their ratings.
     *
     * @return a map of lessons to integer ratings
     */

    public void bookLesson(Lesson lesson) {
        bookedLessons.add(lesson);
    }

    /**
     * Gets a List of Booked lessons with their respective learner.
     *
     * @return a List of lessons to Booked by  learners
     */
    public List<Lesson> getBookedLessons() {
        return bookedLessons;
    }

    public void cancelLesson(Lesson lesson){
        if (bookedLessons.contains(lesson)) {
            // Remove the lesson from booked lessons
            bookedLessons.remove(lesson);

            // Add the lesson to cancelled lessons
            cancelledLessons.add(lesson);
        } else {
            System.out.println("Cannot cancel lesson. Lesson not booked.");
        }
    }

    /**
     * Gets a List of Booked lessons with their respective learner.
     *
     * @return a List of lessons to Cancelled by  learners
     */
    public List<Lesson> getCancelledLessons(){
        return cancelledLessons;
    }

    /**
     * Gets a List of Booked lessons with their respective learner.
     *
     * @return a List of lessons to Attended by  learners
     */
    public List<Lesson> getAttendedLessons(){
        return attendedLessons;
    }

    /**
     * for attend lessons
     * @param lesson
     */
    public void attendLesson(Lesson lesson) {
        if (bookedLessons.contains(lesson)) {
            // Remove the lesson from booked lessons
            bookedLessons.remove(lesson);

            // Add the lesson to attended lessons
            attendedLessons.add(lesson);

            // Check if the attended lesson's grade level is higher
            // If yes, upgrade the learner's grade level
            if (lesson.getGradeLevel() > gradeLevel) {
                upgradeGradeLevel();
            }
        } else {
            System.out.println("Cannot attend lesson. Lesson not booked.");
        }

    }

    /**
     * changes booking , remove old lessons from bookeds lessons and add new lesson
     * @param oldLesson
     * @param newLesson
     */
    public void changeBooking(Lesson oldLesson, Lesson newLesson) {
        if (bookedLessons.contains(oldLesson)) {
            // Remove the old lesson from booked lessons
            bookedLessons.remove(oldLesson);

            // Add the new lesson to booked lessons
            bookedLessons.add(newLesson);
        } else {
            System.out.println("Cannot change booking. Lesson not booked.");
        }
    }

    public int getTotalBookedLessons() {
        return bookedLessons.size();
    }

    public int getTotalCancelledLessons() {
        return cancelledLessons.size();
    }

    public int getTotalAttendedLessons() {
        return attendedLessons.size();
    }


}
