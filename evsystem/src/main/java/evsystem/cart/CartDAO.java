// NOTE: This class is preserved for future support of database-backed cart persistence.
// Currently not used — the CartController uses an in-memory solution for demo purposes.

package evsystem.cart;

import evsystem.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) class responsible for performing
 * database operations related to the shopping cart.
 */
public class CartDAO {

    /**
     * Adds a vehicle to the user's cart in the database.
     */
    public void addToCart(CartItem item) {
        String sql = "INSERT INTO cart_items(user_id, vehicle_id) VALUES(?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getUserId());
            pstmt.setInt(2, item.getVehicleId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding to cart: " + e.getMessage());
        }
    }

    /**
     * Removes a specific vehicle from the user's cart.
     */
    public void removeFromCart(CartItem item) {
        String sql = "DELETE FROM cart_items WHERE user_id = ? AND vehicle_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getUserId());
            pstmt.setInt(2, item.getVehicleId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error removing from cart: " + e.getMessage());
        }
    }

    /**
     * Retrieves all vehicle IDs in a given user's cart.
     */
    public List<Integer> getUserCart(String userId) {
        List<Integer> vehicles = new ArrayList<>();
        String sql = "SELECT vehicle_id FROM cart_items WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                vehicles.add(rs.getInt("vehicle_id"));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving cart: " + e.getMessage());
        }

        return vehicles;
    }

    /**
     * Clears all items from the user's cart.
     */
    public void clearUserCart(String userId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error clearing cart: " + e.getMessage());
        }
    }
}
