/**
 * 
 * 
 * @author joshu
 */

public class Room { 
    
    private String name;
    private int seats;
    private int id;
    
    public Room(String name, int seats){
        
        this.name = name;
        this.seats = seats;
        
    }
    
    public Room(int id, String name, int seats){
        
        this.id = id;
        this.name = name;
        this.seats = seats;
        
    }
    
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
    
}
