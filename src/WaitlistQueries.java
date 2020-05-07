import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements the database queries for the waitlist including adding entries and selecting by specific field values and converting an entry into a printable string.
 */

public class WaitlistQueries { 
    
    // Connection vars
    private static Connection connection;
    private static ArrayList<String> waitlistRequests = new ArrayList<String>();
    private static PreparedStatement waitlistStatement;
    private static ResultSet resultSet;
    
    // Method to add a waitlist entry to the waitlist using an insert query
    public static void addWaitlist(WaitlistEntry waitlistEntry){
        
        connection = DBConnection.getConnection();
        try{
            waitlistStatement = connection.prepareStatement("INSERT INTO waitlist (faculty, date, timestamp, seats) values (?,?,?,?)");
            waitlistStatement.setInt(1, waitlistEntry.getFaculty());
            waitlistStatement.setString(2, waitlistEntry.getDate());
            waitlistStatement.setTimestamp(3, waitlistEntry.getTimestamp());
            waitlistStatement.setInt(4, waitlistEntry.getSeats());
            waitlistStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    // Method to remove a waitlist entry using a delete query
    public static void deleteWaitlist(WaitlistEntry waitlistEntry){
        
        connection = DBConnection.getConnection();
        try{
            
            if(waitlistEntry.getId() != -1){
                waitlistStatement = connection.prepareStatement("DELETE FROM waitlist WHERE rowid=?");
                waitlistStatement.setInt(1, waitlistEntry.getId());
            }
            else{
                waitlistStatement = connection.prepareStatement("DELETE FROM waitlist WHERE faculty=? AND seats=? AND date=?");
                waitlistStatement.setInt(1, waitlistEntry.getFaculty());
                waitlistStatement.setInt(2, waitlistEntry.getSeats());
                waitlistStatement.setString(3, waitlistEntry.getDate());
            }
            
            waitlistStatement.executeUpdate();
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    // Method to find waitlist entry given faculty and date
    public static WaitlistEntry findEntry(int faculty, String date){
        
        connection = DBConnection.getConnection();
        try{
            waitlistStatement = connection.prepareStatement("SELECT rowid, faculty, date, seats, timestamp FROM waitlist WHERE faculty=? AND date=?");
            waitlistStatement.setInt(1, faculty);
            waitlistStatement.setString(2, date);
            resultSet = waitlistStatement.executeQuery();
            
            while(resultSet.next()){
                return new WaitlistEntry(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getTimestamp(5));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
        
    }
    
    // Method to retrieve the entire waitlist as arraylist of waitlist entries using select query
    public static ArrayList<WaitlistEntry> getWaitlist() {
        
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> returnWaitlist = new ArrayList<WaitlistEntry>();
        try{
            
            waitlistStatement = connection.prepareStatement("select rowid, faculty, date, seats, timestamp from waitlist order by date");
            resultSet = waitlistStatement.executeQuery();
            
            while(resultSet.next()){
                returnWaitlist.add(new WaitlistEntry(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getTimestamp(5)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnWaitlist;
        
    }
    
    // Method to retrieve printable string form of given Waitlist entry
    public static String getWaitlistString(WaitlistEntry waitlist){
        
        connection = DBConnection.getConnection();
        String output = "ERROR";
        try{
            
            waitlistStatement = connection.prepareStatement("SELECT faculty.name, waitlist.seats FROM faculty, waitlist WHERE faculty.id IN (SELECT faculty FROM waitlist WHERE rowid=?) AND waitlist.seats IN (SELECT seats FROM waitlist WHERE rowid=?) ORDER BY waitlist.date");
            waitlistStatement.setInt(1, waitlist.getId());
            waitlistStatement.setInt(2, waitlist.getId());
            resultSet = waitlistStatement.executeQuery();
            
            while(resultSet.next()){
                output = resultSet.getString(1) + " waiting for " + resultSet.getString(2) + " seats on " + waitlist.getDate();
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return output;
        
    }
    
}
