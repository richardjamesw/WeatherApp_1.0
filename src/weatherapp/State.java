package weatherapp;

/**
 *
 * @author richa
 */
public class State extends Object{
    private String name;
    private String id;
    
    public State(String id, String name){
        this.id = id;
        this.name = name;
    }
    
    public void setName(String n){
        name = n;
    }
    
    public void setID(String i){
        id = i;
    }
    
    public String getName(){
        return name;
    }
    
    public String getID(){
        return id;
    }
    
}