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

public class RoomQueries { 
    
    private static Connection connection;
    private static ArrayList<Room> faculty = new ArrayList<Room>();
    private static PreparedStatement roomStatement;
    private static ResultSet resultSet;
    
    public static ArrayList<Room> getRoomList() {
        
        connection = DBConnection.getConnection();
        ArrayList<Room> returnRooms = new ArrayList<Room>();
        try{
            
            roomStatement = connection.prepareStatement("select id, name, seats from rooms order by seats");
            resultSet = roomStatement.executeQuery();
            
            while(resultSet.next()){
                returnRooms.add(new Room(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnRooms;
        
    }
    
}
