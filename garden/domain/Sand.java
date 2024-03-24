package domain;
import java.awt.Color;

/**
 * Write a description of class Sand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sand implements Thing
{
    private Color color;
    private Garden garden;
    private int row,column;
    private int time;
    /**
     * Constructor for objects of class Sand
     */
    public Sand(Garden garden,int row, int column){   
        this.garden=garden;
        this.row=row;
        this.column=column;
        garden.setThing(row,column,(Thing)this);  
        color=color.darkGray;
        garden.numberSandBlocks++;
        time = garden.time;
    }
    
    public void act(){
        time++;
        if (time== 100)
        {
         garden.setThing(row,column,null);      
        }
        else{
         color = color.brighter();
        }
        
    }
    
    public final int shape(){
        return Thing.SQUARE;
    }
    
    public final Color getColor(){
        return color;
    }


    
}
