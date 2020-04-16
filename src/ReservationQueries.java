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

public class ReservationQueries { 
    
    private static Connection connection;
    private static ArrayList<String> reservations = new ArrayList<String>();
    private static PreparedStatement reservationStatement;
    private static ResultSet resultSet;
    
    public static void addReservation(ReservationEntry reservation){
        
        connection = DBConnection.getConnection();
        try{
            reservationStatement = connection.prepareStatement("INSERT INTO reservations (faculty, room, date, timestamp, seats) values (?)");
            reservationStatement.setInt(1, reservation.getFaculty());
            reservationStatement.setInt(2, reservation.getRoom());
            reservationStatement.setString(3, reservation.getDate());
            reservationStatement.setTimestamp(4, reservation.getTimestamp());
            reservationStatement.setInt(5, reservation.getSeats());
            reservationStatement.executeUpdate();
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ReservationEntry> getReservationsByDate(String date) {
        
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> returnReservations = new ArrayList<ReservationEntry>();
        try{
            
            reservationStatement = connection.prepareStatement("select faculty, room, timestamp, seats from reservations where date=(?)");
            reservationStatement.setString(1, date);
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                returnReservations.add(new ReservationEntry(resultSet.getInt(1), resultSet.getInt(2), date, resultSet.getInt(5), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnReservations;
        
    }
    
}
