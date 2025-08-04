package evsystem.review;

import java.util.*;

/**
 * Simulates storage of reviews in memory. For production, a database should be used.
 */
public class ReviewDAO {
    // Map: vehicleId → list of reviews
    private static final Map<Integer, List<Review>> reviewMap = new HashMap<>();

    /**
     * Add a new review for a vehicle.
     */
    public void addReview(Review review) {
        reviewMap.putIfAbsent(review.getVehicleId(), new ArrayList<>());
        reviewMap.get(review.getVehicleId()).add(review);
    }

    /**
     * Get all reviews for a given vehicle ID.
     */
    public List<Review> getReviewsForVehicle(int vehicleId) {
        return reviewMap.getOrDefault(vehicleId, Collections.emptyList());
    }

    /**
     * Get average rating for a vehicle.
     */
    public double getAverageRating(int vehicleId) {
        List<Review> reviews = reviewMap.get(vehicleId);
        if (reviews == null || reviews.isEmpty()) return 0.0;

        double sum = 0;
        for (Review r : reviews) sum += r.getRating();
        return sum / reviews.size();
    }
}
