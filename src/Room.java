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

public class Room { 
    
    private static Connection connection;
    private static PreparedStatement roomStatement;
    private static ResultSet resultSet;
    private String name;
    
    public Room(String name){
        
        this.name = name;
        
    }
    
    public String getName(){
        
        return this.name;
        
    }
    
    public static ArrayList<Room> getRoomList() {
        
        connection = DBConnection.getConnection();
        ArrayList<Room> returnRooms = new ArrayList<Room>();
        try{
            
            roomStatement = connection.prepareStatement("select name from rooms order by name");
            resultSet = roomStatement.executeQuery();
            
            while(resultSet.next()){
                returnRooms.add(new Room(resultSet.getString(1)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnRooms;
        
    }
    
}
