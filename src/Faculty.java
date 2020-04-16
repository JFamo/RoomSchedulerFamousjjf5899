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

public class Faculty { 
    
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement facultyStatement;
    private static ResultSet resultSet;
    
    public static void addFaculty(String name){
        
        connection = DBConnection.getConnection();
        try{
            facultyStatement = connection.prepareStatement("INSERT INTO faculty (name) values (?)");
            facultyStatement.setString(1, name);
            facultyStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getFacultyList() {
        
        connection = DBConnection.getConnection();
        ArrayList<String> returnFaculty = new ArrayList<String>();
        try{
            
            facultyStatement = connection.prepareStatement("select name from faculty order by name");
            resultSet = facultyStatement.executeQuery();
            
            while(resultSet.next()){
                returnFaculty.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnFaculty;
        
    }
    
}
