package domain;
import java.awt.Color;


/**
 * Write a description of class Gardener here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gardener extends Agent implements Thing{
    // instance variables - replace the example below with your own
    protected Color color;
    private Garden garden;
    protected int row,column;

    /**
     * Constructor for objects of class Gardener
     */
    public Gardener(Garden garden,int row, int column){
        // initialise instance variables
        this.garden=garden;
        this.row=row;
        this.column=column;
        garden.setThing(row,column,(Thing)this);
        color = color.white;
    }
    
    /**Returns the shape
    @return 
     */
    public final int shape(){
        return Thing.HAT;
    }
    
    public void changeState(char nextState){
    }
    
    /**Returns the row
    @return 
     */
    public final int getRow(){
        return row;
    }
    
    /**Returns tha column
    @return 
     */
    public final int getColumn(){
        return column;
    }
    
    /**Returns the color
    @return 
     */
    public final Color getColor(){
        return color;
    }

    public void move(int row, int column){
    }
    
    public void act(){
        turn();
    }
}
