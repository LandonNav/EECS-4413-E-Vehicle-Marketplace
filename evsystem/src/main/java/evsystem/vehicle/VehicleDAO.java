package evsystem.vehicle;

import java.sql.*; 
import java.util.ArrayList; 
import java.util.List;
import evsystem.database.DatabaseConnection; 

public class VehicleDAO 
{ 
    public List<Vehicle> readAll() 
    { 
        String sql = "SELECT id, model, type, price, rating FROM vehicles"; 
        List<Vehicle> vehicles = new ArrayList<>(); 

        try (Connection conn = DatabaseConnection.connect(); 
             Statement stmt  = conn.createStatement(); 
             ResultSet rs    = stmt.executeQuery(sql))
        { 
            while (rs.next()) 
            { 
                Vehicle vehicle = new Vehicle(); 
                
                vehicle.setID(rs.getInt("id")); 
                vehicle.setModel(rs.getString("model")); 
                vehicle.setType(rs.getString("type")); 
                vehicle.setPrice(rs.getDouble("price")); 
                vehicle.setRating(rs.getInt("rating"));
                
                vehicles.add(vehicle); 
            } 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
        return vehicles; 
    } 

    public void create(Vehicle vehicle) 
    { 
        String sql = "INSERT INTO vehicles(id, model, type, price, rating) VALUES(?,?,?,?,?)"; 

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            pstmt.setInt(1, vehicle.getID()); 
            pstmt.setString(2, vehicle.getModel()); 
            pstmt.setString(3, vehicle.getType());
            pstmt.setDouble(4,  vehicle.getPrice());
            pstmt.setInt(5, vehicle.getRating());
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 

    public Vehicle read(int id) 
    { 
        String sql = "SELECT model, type, price, rating FROM vehicles WHERE id = ?"; 
        Vehicle vehicle = null; 

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            pstmt.setInt(1, id); 
            try (ResultSet rs = pstmt.executeQuery()) 
            { 
                // Check if a result was returned 
                if (rs.next()) 
                { 
                    vehicle = new Vehicle(); 
                    // Set the properties of the student object 
                    vehicle.setID(id); 
                    vehicle.setModel(rs.getString("model")); 
                    vehicle.setType(rs.getString("type")); 
                    vehicle.setPrice(rs.getDouble("price"));
                    vehicle.setRating(rs.getInt("rating"));
                } 
            } 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 

        return vehicle; 
    } 

    public void update(int id, Vehicle vehicle) 
    { 
        String sql = "UPDATE vehicles SET model = ?, type = ?, price = ?, rating = ? WHERE id = ?"; 
         
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            // Set the corresponding parameters 
            pstmt.setString(1, vehicle.getModel()); 
            pstmt.setString(2, vehicle.getType()); 
            pstmt.setDouble(3, vehicle.getPrice());
            pstmt.setInt(4,  vehicle.getRating());
            pstmt.setInt(5, id); 
            // Update the vehicle record 
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 
 
    public void delete(int id) 
    { 
        String sql = "DELETE FROM vehicles WHERE id = ?"; 
         
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            // Set the corresponding parameter 
            pstmt.setInt(1, id); 
            // Delete the vehicle record 
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 

} 