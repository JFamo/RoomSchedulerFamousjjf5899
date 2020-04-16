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

public class Date { 
    
    private static Connection connection;
    private static ArrayList<String> dates = new ArrayList<String>();
    private static PreparedStatement dateStatement;
    private static ResultSet resultSet;
    
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
    
    public static String convertDate(String day, String month, String year){
        
        return year + "-" + month + "-" + day;
        
    }
    
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
