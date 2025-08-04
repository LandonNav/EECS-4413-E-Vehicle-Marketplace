package evsystem.review;

/**
 * Represents a user review for a vehicle.
 */
public class Review {
    private int vehicleId;
    private String username;
    private int rating;       // 1 to 5
    private String comment;

    // Constructors
    public Review() {}

    public Review(int vehicleId, String username, int rating, String comment) {
        this.vehicleId = vehicleId;
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters
    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
