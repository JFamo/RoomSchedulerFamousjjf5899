import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author joshu
 */
public class WaitlistEntry {
    
    private int faculty;
    private String date;
    private int seats;
    private Timestamp timestamp;
    
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
    
}
