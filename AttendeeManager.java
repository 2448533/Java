/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author NANDANA BIJU
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.sql.ResultSet;


public class AttendeeManager {

    public void addAttendee(String fullName, String email, String contactNumber, String country) {
        String query = "INSERT INTO attendees (full_name, email, contact_number, country) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = dbconnect.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, contactNumber);
            ps.setString(4, country);

            ps.executeUpdate();
            System.out.println("Attendee added successfully.");

        } catch (SQLException e) {
            System.out.println("Error adding attendee: " + e.getMessage());
        }
    }
    
    
    public void editAttendee(int attendeeId, String newEmail, String newContactNumber) {
    String query = "UPDATE attendees SET email = ?, contact_number = ? WHERE attendee_id = ?";
    
    try (Connection conn = dbconnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, newEmail);
        ps.setString(2, newContactNumber);
        ps.setInt(3, attendeeId);

        ps.executeUpdate();
        System.out.println("Attendee updated successfully.");

    } catch (SQLException e) {
        System.out.println("Error updating attendee: " + e.getMessage());
    }
}
    
  public void deleteAttendee(int attendeeId) {
    String query = "DELETE FROM attendees WHERE attendee_id = ?";
    
    try (Connection conn = dbconnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setInt(1, attendeeId);

        ps.executeUpdate();
        System.out.println("Attendee deleted successfully.");

    } catch (SQLException e) {
        System.out.println("Error deleting attendee: " + e.getMessage());
    }
}

  public void searchAttendee(String searchCriteria) {
    String query = "SELECT * FROM attendees WHERE full_name LIKE ? OR country LIKE ?";
    
    try (Connection conn = dbconnect.getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {

        ps.setString(1, "%" + searchCriteria + "%");
        ps.setString(2, "%" + searchCriteria + "%");

        var rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("attendee_id"));
            System.out.println("Name: " + rs.getString("full_name"));
            System.out.println("Email: " + rs.getString("email"));
            System.out.println("Contact: " + rs.getString("contact_number"));
            System.out.println("Country: " + rs.getString("country"));
            System.out.println("-------------------------------");
        }

    } catch (SQLException e) {
        System.out.println("Error searching attendee: " + e.getMessage());
    }
}

 public void generateStatistics() {
    String query = "{CALL get_attendee_statistics()}";

    try (Connection conn = dbconnect.getConnection();
         CallableStatement cs = conn.prepareCall(query)) {

        ResultSet rs = cs.executeQuery();
        while (rs.next()) {
            System.out.println("Country: " + rs.getString("country"));
            System.out.println("Attendees: " + rs.getInt("num_attendees"));
            System.out.println("-------------------------------");
        }

    } catch (SQLException e) {
        System.out.println("Error generating statistics: " + e.getMessage());
    }
}

}

