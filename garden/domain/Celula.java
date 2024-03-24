package domain;
import java.awt.Color;

/**
 * Write a description of class Celula here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Celula extends Agent implements Thing
{
    protected char nextState;
    protected Color color;
    private Garden garden;
    protected int row,column;
   

    public Celula(Garden garden,int row, int column){
        this.garden=garden;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        garden.setThing(row,column,(Thing)this);  
        color=Color.red;

        setTime(garden.time);
    }
    public void move(int row, int column){}
    public void changeState(char nextState){
        state = nextState;
    }
    public void act()
    {
        turn();
        changeState(nextState);
         if(getTime() == 5){
            nextState=Agent.DEAD;
            changeState(nextState);
            color = Color.orange;
        }
    }
     public final int shape(){
        return Thing.ROUND;
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
    
}