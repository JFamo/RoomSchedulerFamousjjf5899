import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements the database queries for a room including a select to retrieve all rooms and insert to add new rooms.
 */

public class RoomQueries { 
    
    // Connection vars
    private static Connection connection;
    private static ArrayList<Room> faculty = new ArrayList<Room>();
    private static PreparedStatement roomStatement;
    private static ResultSet resultSet;
    
    // Method to retrieve list of rooms using select query
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
    
    // Method to add room to database using insert query
    public static void addRoom(String name, int seats){
        
        connection = DBConnection.getConnection();
        try{
            roomStatement = connection.prepareStatement("INSERT INTO rooms (name,seats) values (?,?)");
            roomStatement.setString(1, name);
            roomStatement.setInt(2, seats);
            roomStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
}
