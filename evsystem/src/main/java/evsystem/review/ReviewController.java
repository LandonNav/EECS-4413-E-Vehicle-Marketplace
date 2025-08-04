package evsystem.review;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

/**
 * REST controller for handling reviews.
 */
@Path("/reviews")
public class ReviewController {

    private final ReviewDAO reviewDAO = new ReviewDAO();

    /**
     * Submit a review.
     * POST /reviews/submit
     */
    @POST
    @Path("/submit")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String submitReview(@FormParam("vehicleId") int vehicleId,
                               @FormParam("username") String username,
                               @FormParam("rating") int rating,
                               @FormParam("comment") String comment) {
        if (rating < 1 || rating > 5) {
            return "Rating must be between 1 and 5.";
        }

        Review review = new Review(vehicleId, username, rating, comment);
        reviewDAO.addReview(review);

        return "Review submitted for vehicle " + vehicleId;
    }

    /**
     * Get all reviews for a vehicle.
     * GET /reviews/vehicle/{id}
     */
    @GET
    @Path("/vehicle/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Review> getReviews(@PathParam("id") int vehicleId) {
        return reviewDAO.getReviewsForVehicle(vehicleId);
    }

    /**
     * Get average rating for a vehicle.
     * GET /reviews/average/{id}
     */
    @GET
    @Path("/average/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAverageRating(@PathParam("id") int vehicleId) {
        double avg = reviewDAO.getAverageRating(vehicleId);
        return String.format("Average rating for vehicle %d: %.2f", vehicleId, avg);
    }
}
