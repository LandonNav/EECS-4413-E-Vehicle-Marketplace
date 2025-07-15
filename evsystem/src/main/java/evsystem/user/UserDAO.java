package evsystem.user;

import java.sql.*; 
import java.util.ArrayList; 
import java.util.List;
import evsystem.database.DatabaseConnection; 

public class UserDAO 
{ 
    public List<User> readAll() 
    { 
        String sql = "SELECT id, username, password, type FROM users"; 
        List<User> users = new ArrayList<>(); 

        try (Connection conn = DatabaseConnection.connect(); 
             Statement stmt  = conn.createStatement(); 
             ResultSet rs    = stmt.executeQuery(sql))
        { 
            while (rs.next()) 
            { 
                User user = new User(); 
                
                user.setID(rs.getInt("id")); 
                user.setName(rs.getString("username")); 
                user.setPassword(rs.getString("password")); 
                user.setType(rs.getString("type")); 
                
                users.add(user); 
            } 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
        return users; 
    } 

    public void create(User user) 
    { 
        String sql = "INSERT INTO users(id, usename, password, type) VALUES(?,?,?,?)"; 

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            pstmt.setInt(1, user.getID()); 
            pstmt.setString(2, user.getName()); 
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4,  user.getType());
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 

    public User read(int id) 
    { 
        String sql = "SELECT username, password, type FROM users WHERE id = ?"; 
        User user = null; 

        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            pstmt.setInt(1, id); 
            try (ResultSet rs = pstmt.executeQuery()) 
            { 
                // Check if a result was returned 
                if (rs.next()) 
                { 
                    user = new User(); 
                    // Set the properties of the student object 
                    user.setID(id); 
                    user.setName(rs.getString("username")); 
                    user.setPassword(rs.getString("password")); 
                    user.setType(rs.getString("type"));
                } 
            } 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 

        return user; 
    } 

    public void update(int id, User user) 
    { 
        String sql = "UPDATE users SET username = ?, password = ?, type = ? WHERE id = ?"; 
         
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            // Set the corresponding parameters 
            pstmt.setString(1, user.getName()); 
            pstmt.setString(2, user.getPassword()); 
            pstmt.setString(3, user.getType());
            pstmt.setInt(4, id); 
            // Update the user record 
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 
 
    public void delete(int id) 
    { 
        String sql = "DELETE FROM users WHERE id = ?"; 
         
        try (Connection conn = DatabaseConnection.connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) 
        { 
            // Set the corresponding parameter 
            pstmt.setInt(1, id); 
            // Delete the user record 
            pstmt.executeUpdate(); 
        } 
        catch (SQLException e) 
        { 
            System.out.println(e.getMessage()); 
        } 
    } 

} 