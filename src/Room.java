/**
 * 
 * 
 * @author joshu
 */

public class Room { 
    
    private String name;
    private int seats;
    
    public Room(String name, int seats){
        
        this.name = name;
        this.seats = seats;
        
    }
    
    public String getName(){
        
        return this.name;
        
    }
    
    public int getSeats(){
        
        return this.seats;
        
    }
    
}
