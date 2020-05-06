import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a single reservation entry in the reservations table including the ID of a faculty and ID of a room being reserved and the unique row id of the reservation.
 */

public class ReservationEntry extends Request {
    
    // Instance vars
    private int room;
    
    // Constructors
    public ReservationEntry(int faculty, int room, String date, int seats){
        
        super(faculty, date, seats);
        this.room = room;
        
    }
    
    public ReservationEntry(int faculty, int room, String date, int seats, Timestamp timestamp){
        
        super(faculty, date, seats, timestamp);
        this.room = room;
        
    }
    
    public ReservationEntry(int id, int faculty, int room, String date, int seats, Timestamp timestamp){
        
        super(id, faculty, date, seats, timestamp);
        this.room = room;
        
    }
    
    // Accessors
    
    public int getRoom(){
        return room;
    }
    
}
