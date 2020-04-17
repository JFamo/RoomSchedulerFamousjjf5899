import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author joshu
 */
public class ReservationEntry {
    
    private int faculty;
    private int room;
    private String date;
    private int seats;
    private int id;
    private Timestamp timestamp;
    
    public ReservationEntry(int faculty, int room, String date, int seats){
        
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        timestamp = new Timestamp(System.currentTimeMillis());
        
    }
    
    public ReservationEntry(int faculty, int room, String date, int seats, Timestamp timestamp){
        
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
        
    }
    
    public ReservationEntry(int id, int faculty, int room, String date, int seats, Timestamp timestamp){
        
        this.id = id;
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
        this.timestamp = timestamp;
        
    }
    
    public int getFaculty(){
        return faculty;
    }
    
    public int getRoom(){
        return room;
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
