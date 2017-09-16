
package weatherapp;

/**
 *
 * @author richa
 */
public class Station extends Object{
    private String name;
    private String id;
    private int temp;
    
    public Station(String id, String name){
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
    
    public void setAveTemp(int t){
        temp = t;
    }
    
    public int getAveTemp(){
        return temp;
    }
    
    
}
