import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * 
 * @author joshu
 */

public class WaitlistQueries { 
    
    private static Connection connection;
    private static ArrayList<String> waitlistRequests = new ArrayList<String>();
    private static PreparedStatement waitlistStatement;
    private static ResultSet resultSet;
    
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
    
    public static ArrayList<WaitlistEntry> getWaitlist() {
        
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> returnWaitlist = new ArrayList<WaitlistEntry>();
        try{
            
            waitlistStatement = connection.prepareStatement("select faculty, date, seats, timestamp from waitlist order by date");
            resultSet = waitlistStatement.executeQuery();
            
            while(resultSet.next()){
                returnWaitlist.add(new WaitlistEntry(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnWaitlist;
        
    }
    
}
