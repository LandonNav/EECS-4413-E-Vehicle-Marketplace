// NOTE: This class is preserved for future support of database-backed cart persistence.
// Currently not used — the CartController uses an in-memory solution for demo purposes.

package evsystem.cart;

/**
 * Represents a single item in a user's shopping cart.
 * Each cart item corresponds to a specific user and a specific vehicle.
 */
public class CartItem {
    private String userId;     // Unique identifier for the user
    private int vehicleId;     // ID of the vehicle added to the cart

    // Default constructor (required for frameworks like Jackson)
    public CartItem() {}

    // Constructor with parameters
    public CartItem(String userId, int vehicleId) {
        this.userId = userId;
        this.vehicleId = vehicleId;
    }

    // Getter and Setter methods
    public String getUserId() {
        return userId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
}
