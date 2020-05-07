import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements the database queries for a reservation entry with options to add, retrieve based on field, and convert to printable string using SQL joins.
 */

public class ReservationQueries { 
    
    // Connection vars
    private static Connection connection;
    private static ArrayList<String> reservations = new ArrayList<String>();
    private static PreparedStatement reservationStatement;
    private static ResultSet resultSet;
    
    // Method to add reservation using insert query
    public static void addReservation(ReservationEntry reservation){
        
        connection = DBConnection.getConnection();
        try{
            reservationStatement = connection.prepareStatement("INSERT INTO reservations (faculty, room, date, timestamp, seats) values (?,?,?,?,?)");
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
    
    // Method to remove reservation using delete query
    public static void deleteReservation(ReservationEntry reservationEntry){
        
        connection = DBConnection.getConnection();
        try{
            
            if(reservationEntry.getId() != -1){
                reservationStatement = connection.prepareStatement("DELETE FROM reservations WHERE rowid=?");
                reservationStatement.setInt(1, reservationEntry.getId());
            }
            else{
                reservationStatement = connection.prepareStatement("DELETE FROM reservations WHERE faculty=? AND date=?");
                reservationStatement.setInt(1, reservationEntry.getFaculty());
                reservationStatement.setString(2, reservationEntry.getDate());
            }
            
            reservationStatement.executeUpdate();
            
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        
    }
    
    // Method to find reservation entry given faculty and date
    public static ReservationEntry findEntry(int faculty, String date){
        
        connection = DBConnection.getConnection();
        try{
            reservationStatement = connection.prepareStatement("SELECT rowid, faculty, room, timestamp, seats, date FROM reservations WHERE faculty=? AND date=?");
            reservationStatement.setInt(1, faculty);
            reservationStatement.setString(2, date);
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                return new ReservationEntry(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), resultSet.getString(6), resultSet.getInt(5), resultSet.getTimestamp(4));
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
        
    }
    
    // Method to retrieve reservations by date. Takes date in string format and returns arraylist of reservation entries
    public static ArrayList<ReservationEntry> getReservationsByDate(String date) {
        
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> returnReservations = new ArrayList<ReservationEntry>();
        try{
            
            reservationStatement = connection.prepareStatement("select rowid, faculty, room, timestamp, seats from reservations where date=(?)");
            reservationStatement.setString(1, date);
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                returnReservations.add(new ReservationEntry(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3), date, resultSet.getInt(5), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnReservations;
        
    }
    
    // Method to retrieve reservation entries by room using select query. Takes room id as integer and returns arraylist of reservation entries
    public static ArrayList<ReservationEntry> getReservationsByRoom(int room) {
        
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> returnReservations = new ArrayList<ReservationEntry>();
        try{
            
            reservationStatement = connection.prepareStatement("select rowid, faculty, date, timestamp, seats from reservations where room=(?)");
            reservationStatement.setInt(1, room);
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                returnReservations.add(new ReservationEntry(resultSet.getInt(1), resultSet.getInt(2), room, resultSet.getString(3), resultSet.getInt(5), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnReservations;
        
    }
    
    // Method to retrieve all reservation entries using select query
    public static ArrayList<ReservationEntry> getReservations() {
        
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> returnReservations = new ArrayList<ReservationEntry>();
        try{
            
            reservationStatement = connection.prepareStatement("select rowid, faculty, date, timestamp, seats, room from reservations");
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                returnReservations.add(new ReservationEntry(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(6), resultSet.getString(3), resultSet.getInt(5), resultSet.getTimestamp(4)));
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return returnReservations;
        
    }
    
    // Method to get printable string for a given reservation entry
    public static String getReservationString(ReservationEntry reservation){
        
        connection = DBConnection.getConnection();
        String output = "ERROR";
        try{
            
            reservationStatement = connection.prepareStatement("SELECT faculty.name, rooms.name FROM faculty, rooms WHERE faculty.id IN (SELECT faculty FROM reservations WHERE rowid=(?)) AND rooms.id IN (SELECT room FROM reservations WHERE rowid=(?))");
            reservationStatement.setInt(1, reservation.getId());
            reservationStatement.setInt(2, reservation.getId());
            resultSet = reservationStatement.executeQuery();
            
            while(resultSet.next()){
                output = resultSet.getString(2) + " reserved by " + resultSet.getString(1) + " on " + reservation.getDate();
            }
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
            
        }
        
        return output;
        
    }
    
}
