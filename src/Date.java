import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a date object which is valid for reservations.
 */

public class Date { 
    
    // Connection vars
    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement dateStatement;
    private static ResultSet resultSet;
    
    // Method to add a date to the database as a String using day, month, and year
    public static void addDate(String day, String month, String year){
        
        connection = DBConnection.getConnection();
        try{
            dateStatement = connection.prepareStatement("INSERT INTO dates (date) values (?)");
            dateStatement.setString(1, convertDate(day, month, year));
            dateStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    // Method to format day, month, and year strings for SQL compatibility
    public static String convertDate(String day, String month, String year){
        
        return year + "-" + month + "-" + day;
        
    }
    
    // Method to retrieve all valid dates as an arraylist using select query. Returns an arraylist of strings representing SQL dates
    public static ArrayList<String> getDateList() {
        
        connection = DBConnection.getConnection();
        ArrayList<String> returnDates = new ArrayList<String>();
        try{
            
            dateStatement = connection.prepareStatement("select date from dates order by date");
            resultSet = dateStatement.executeQuery();
            
            while(resultSet.next()){
                returnDates.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnDates;
        
    }
    
}
