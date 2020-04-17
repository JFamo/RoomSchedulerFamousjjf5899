import java.util.Date;
import java.sql.Timestamp;

/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a single waitlist entry which includes the id of the reserving faculty, id of the room reserved, and unique row id of this waitlist entry.
 */

public class WaitlistEntry {
    
    // Instance vars
    private int id;
    private int faculty;
    private String date;
    private int seats;
    private Timestamp timestamp;
    
    // Constructors
    public WaitlistEntry(int faculty, String date, int seats){
        
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        timestamp = new Timestamp(System.currentTimeMillis());
        
    }
    
    public WaitlistEntry(int faculty, String date, int seats, Timestamp timestamp){
        
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
        
    }
    
    public WaitlistEntry(int id, int faculty, String date, int seats, Timestamp timestamp){
        
        this.id = id;
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
        
    }
    
    // Accessors
    public int getFaculty(){
        return faculty;
    }
    
    public String getDate(){
        return date;
    }
    
    public int getSeats(){
        return seats;
    }
    
    public Timestamp getTimestamp(){
        return timestamp;
    }
    
    public int getId(){
        if(id > 0){
            return id;
        }
        else{
            return -1;
        }
    }
    
}
