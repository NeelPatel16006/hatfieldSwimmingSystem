package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coach {
    private final String name;
    private final List<Integer> ratings;

    public Coach(String name) {
        this.name = name;
        this.ratings = new ArrayList<>();
    }

    /**
     * add rating to check the rating between 1 and 5 and add rating
     * @param rating
     */
    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {
            this.ratings.add(rating);
        } else {
            System.out.println("Invalid rating. Must be between 1 and 5.");
        }
    }

    /**
     * calculate average rating for coach
     * @return average rating
     */
    public double getAverageRating() {
        if (this.ratings.isEmpty()) {
            return 0.0;
        } else {
            double sum = 0.0;

            int rating;
            for(Iterator<Integer> var3 = this.ratings.iterator(); var3.hasNext(); sum += (double)rating) {
                rating = (Integer)var3.next();
            }

            return sum / (double)this.ratings.size();
        }
    }

    public String getName() {
        return this.name;
    }

}
