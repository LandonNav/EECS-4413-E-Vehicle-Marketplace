package evsystem.vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import evsystem.database.DatabaseConnection;

public class VehicleDAO {

    /**
     * Reads all vehicle records from the database.
     */
    public List<Vehicle> readAll() {
        String sql = "SELECT id, model, type, price, rating FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setID(rs.getInt("id"));
                vehicle.setModel(rs.getString("model"));
                vehicle.setType(rs.getString("type"));
                vehicle.setPrice(rs.getDouble("price"));
                vehicle.setRating(rs.getInt("rating"));
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vehicles;
    }

    /**
     * Inserts a new vehicle record into the database.
     */
    public void create(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles(id, model, type, price, rating) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, vehicle.getID());
            pstmt.setString(2, vehicle.getModel());
            pstmt.setString(3, vehicle.getType());
            pstmt.setDouble(4, vehicle.getPrice());
            pstmt.setInt(5, vehicle.getRating());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Fetches a single vehicle by ID.
     */
    public Vehicle read(int id) {
        String sql = "SELECT model, type, price, rating FROM vehicles WHERE id = ?";
        Vehicle vehicle = null;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    vehicle = new Vehicle();
                    vehicle.setID(id);
                    vehicle.setModel(rs.getString("model"));
                    vehicle.setType(rs.getString("type"));
                    vehicle.setPrice(rs.getDouble("price"));
                    vehicle.setRating(rs.getInt("rating"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return vehicle;
    }

    /**
     * Updates an existing vehicle record by ID.
     */
    public void update(int id, Vehicle vehicle) {
        String sql = "UPDATE vehicles SET model = ?, type = ?, price = ?, rating = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vehicle.getModel());
            pstmt.setString(2, vehicle.getType());
            pstmt.setDouble(3, vehicle.getPrice());
            pstmt.setInt(4, vehicle.getRating());
            pstmt.setInt(5, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a vehicle record by ID.
     */
    public void delete(int id) {
        String sql = "DELETE FROM vehicles WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Filters vehicles by type, price, rating, and allows sorting by fields.
     * Supports dynamic query construction based on non-null parameters.
     */
    public List<Vehicle> filterVehicles(String type, Double minPrice, Double maxPrice, Integer minRating, String sortBy, String order) {
        StringBuilder sql = new StringBuilder("SELECT * FROM vehicles WHERE 1=1");

        // Add filters if they are provided
        if (type != null) sql.append(" AND type = ?");
        if (minPrice != null) sql.append(" AND price >= ?");
        if (maxPrice != null) sql.append(" AND price <= ?");
        if (minRating != null) sql.append(" AND rating >= ?");

        // Validate and append ORDER BY clause
        List<String> validSortFields = List.of("price", "rating", "model");
        List<String> validOrders = List.of("asc", "desc");

        if (sortBy != null && validSortFields.contains(sortBy.toLowerCase())) {
            sql.append(" ORDER BY ").append(sortBy); // safe due to whitelist

            if (order != null && validOrders.contains(order.toLowerCase())) {
                sql.append(" ").append(order.toUpperCase());
            } else {
                sql.append(" ASC"); // default
            }
        }

        List<Vehicle> filtered = new ArrayList<>();

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            // Dynamically bind parameters
            int index = 1;
            if (type != null) pstmt.setString(index++, type);
            if (minPrice != null) pstmt.setDouble(index++, minPrice);
            if (maxPrice != null) pstmt.setDouble(index++, maxPrice);
            if (minRating != null) pstmt.setInt(index++, minRating);

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Vehicle v = new Vehicle();
                v.setID(rs.getInt("id"));
                v.setModel(rs.getString("model"));
                v.setType(rs.getString("type"));
                v.setPrice(rs.getDouble("price"));
                v.setRating(rs.getInt("rating"));
                filtered.add(v);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return filtered;
    }
}
