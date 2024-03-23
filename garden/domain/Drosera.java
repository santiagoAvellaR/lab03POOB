package domain;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Color;

/**
 * Write a description of class Drosera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Drosera extends Flower
{
    // instance variables - replace the example below with your own
    private Garden garden;
    private int daysWithoutEating = 0;

    /**
     * Constructor for objects of class Drosera
     */
    public Drosera(Garden garden, int row, int column){
        // initialise instance variables
        super(garden, row, column);
        this.garden = garden;
        this.color = color.green;
        nextState=Agent.ALIVE;
        changeState(nextState);
    }
    
    private int[] findClosestFlowerAliveOrWater(int targetRow, int targetColumn, boolean eat) {
        int[] closestPosition = new int[]{-1, -1};
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 39; j++) {
                if ((garden.getThing(i, j) instanceof Flower)&& eat && !(garden.getThing(i, j) instanceof Drosera)) {
                    Flower flower = (Flower) garden.getThing(i, j);
                    if (flower.isAlive()) {
                        int distance = Math.abs(targetRow - i) + Math.abs(targetColumn - j);
                        if (distance < minDistance) {
                            minDistance = distance;
                            closestPosition[0] = i;
                            closestPosition[1] = j;
                        }
                    }
                }
                else if (garden.getThing(i, j) instanceof Water && !eat) {
                    Water water = (Water) garden.getThing(i, j);
                    int distance = Math.abs(targetRow - i) + Math.abs(targetColumn - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                        closestPosition[0] = i;
                        closestPosition[1] = j;
                    }
                }
            }
        }
        return closestPosition;
    }
    
    @Override
    public void act() {
        System.out.println("tiempo del jardin: " + garden.time + " tiempo de la Drosera: " + getTime());
        if (getTime()==garden.time){
            boolean eat = getTime()%2==0;
            int[] closestFlowerPosition = findClosestFlowerAliveOrWater(row, column, eat);
            if ((closestFlowerPosition[0] != -1 && closestFlowerPosition[1] != -1) && isAlive()) {
                if(eat){
                    Flower flower = (Flower) garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]);
                    flower.changeState('d');
                    daysWithoutEating = 0;
                }
                else{
                    daysWithoutEating++;
                }
                move(closestFlowerPosition[0], closestFlowerPosition[1]);
            }
            else{
                daysWithoutEating++;
                if(daysWithoutEating >= 3){
                    color = Color.black;
                    changeState('d');
                }
            }
            turn();
        }
    }  
    
    @Override
    public void move(int nRow, int nColumn){
        garden.setThing(row, column, null);
        row = nRow;
        column = nColumn;
        garden.setThing(nRow, nColumn, this);
    }
}
