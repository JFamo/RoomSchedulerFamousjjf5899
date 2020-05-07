import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a faculty member, including local variables for use as a JComboBox object and methods for database manipulation of faculty.
 */

public class Faculty { 
    
    // Connection vars
    private static Connection connection;
    private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    private static PreparedStatement facultyStatement;
    private static ResultSet resultSet;
    
    // Instance vars
    private String name;
    private int id;
    
    // Constructors
    public Faculty(String name){
        this.name = name;
    }
    
    public Faculty(int id, String name){
        this.name = name;
        this.id = id;
    }
    
    // Accessors
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    // Override tostring so faculty ID's are compatible with JComboBox as with HTML select
    @Override
    public String toString(){
        return name;
    }
    
    // Method to add faculty to database using insert query
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
    
    // Method to retrieve faculty using select query. Returns an arraylist of faculty objects
    public static ArrayList<Faculty> getFacultyList() {
        
        connection = DBConnection.getConnection();
        ArrayList<Faculty> returnFaculty = new ArrayList<Faculty>();
        try{
            
            facultyStatement = connection.prepareStatement("select id, name from faculty order by name");
            resultSet = facultyStatement.executeQuery();
            
            while(resultSet.next()){
                returnFaculty.add(new Faculty(resultSet.getInt(1),resultSet.getString(2)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnFaculty;
        
    }
    
    // Method to retrieve faculty name using select query. Returns a string and takes an integer
    public static String getFacultyName(int id) {
        
        connection = DBConnection.getConnection();
        try{
            
            facultyStatement = connection.prepareStatement("select name from faculty where id=?");
            facultyStatement.setInt(1, id);
            resultSet = facultyStatement.executeQuery();
            
            while(resultSet.next()){
                return resultSet.getString(1);
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return "ERROR";
        
    }
    
}
