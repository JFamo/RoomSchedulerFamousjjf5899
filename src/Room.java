/**
 * @author Joshua Famous
 * 
 * This program replicates room selection using a Derby database with a Swing GUI for a small college.
 * This class implements a single room which includes a name, unique id, and integer number of seats.
 */

public class Room { 
    
    // Instance vars
    private String name;
    private int seats;
    private int id;
    
    // Constructors
    public Room(String name, int seats){
        
        this.name = name;
        this.seats = seats;
        
    }
    
    public Room(int id, String name, int seats){
        
        this.id = id;
        this.name = name;
        this.seats = seats;
        
    }
    
    // Accessors
    public String getName(){
        
        return this.name;
        
    }
    
    public int getSeats(){
        
        return this.seats;
        
    }
    
    public int getId(){
        
        if(id > 0){
           return this.id; 
        }
        else{
            return -1;
        }
        
    }
    
    // Override tostring so room names are compatible with JComboBox
    @Override
    public String toString(){
        return this.name;
    }
    
}
