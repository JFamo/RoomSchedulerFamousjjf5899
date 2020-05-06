import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a single waitlist entry which includes the id of the reserving faculty, id of the room reserved, and unique row id of this waitlist entry.
 */

public class WaitlistEntry extends Request{
    
    // Constructors
    public WaitlistEntry(int faculty, String date, int seats){
        
        super(faculty, date, seats);
        
    }
    
    public WaitlistEntry(int faculty, String date, int seats, Timestamp timestamp){
        
        super(faculty, date, seats, timestamp);
        
    }
    
    public WaitlistEntry(int id, int faculty, String date, int seats, Timestamp timestamp){
        
        super(id, faculty, date, seats, timestamp);
        
    }
    
}
