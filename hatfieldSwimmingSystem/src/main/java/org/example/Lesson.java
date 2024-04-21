package org.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Represents a swimming lesson at HJSS.
 * Each lesson has a specific day, time slot, grade level, and is assigned a coach and learners.
 * This Class Supports booking and cancellation, and tracks learner reviews and ratings.
 */
public class Lesson {
    private final String day;
    private final String timeSlot;
    private final int gradeLevel;
    private final Coach coach;
    private final Set<Learner> learners;
    private final int lessonID;

    /**
     * Constructs a new Lesson with the specified details.
     *
     * @param day        the day of the week the lesson takes place
     * @param timeSlot   the time slot of the lesson
     * @param gradeLevel the grade level of the lesson
     * @param coach      the coach assigned to the lesson
     * @param lessonID   the lessonID assign to the each lesson
     */
    public Lesson(String day, String timeSlot, int gradeLevel, Coach coach,int lessonID) {
        this.day = day;
        this.timeSlot = timeSlot;
        this.gradeLevel = gradeLevel;
        this.coach = coach;
        this.lessonID = lessonID;
        this.learners = new HashSet<>();
    }


    /**
     * book the lesson for a learner. Checks for space availability,
     * grade level eligibility, and avoids duplicates.
     *
     * @param learner the learner attempting to book the lesson
     */
    public void bookLesson(Learner learner) {
        int maxGradeLevel = this.gradeLevel + 1; // Allow learners up to one level higher

        if (this.learners.size() < 4 && !this.learners.contains(learner) && (learner.getGradeLevel() == this.gradeLevel || learner.getGradeLevel() == maxGradeLevel)) {
            this.learners.add(learner);
        }
    }

    public String getDay() {
        return this.day;
    }

    public String getTimeSlot() {
        return this.timeSlot;
    }

    public int getGradeLevel() {
        return this.gradeLevel;
    }

    public Coach getCoach() {
        return this.coach;
    }

    public int getLessonID(){
        return this.lessonID;
    }

    public Set<Learner> getLearners() {
        return new HashSet<>(this.learners);
    }

}
