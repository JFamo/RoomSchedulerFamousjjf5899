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
    private static ArrayList<Faculty> faculty = new ArrayList<Faculty>();
    private static PreparedStatement facultyStatement;
    private static ResultSet resultSet;
    
    private String name;
    private int id;
    
    public Faculty(String name){
        this.name = name;
    }
    
    public Faculty(int id, String name){
        this.name = name;
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public String toString(){
        return name;
    }
    
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
    
}
