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
    private int daysWithouEating = 0;

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
                if ((garden.getThing(i, j) instanceof Flower || garden.getThing(i, j) instanceof Carnivorous)&& eat) {
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
        boolean eat = getTime()%2==0;
        int[] closestFlowerPosition = findClosestFlowerAliveOrWater(row, column, eat);
        if (closestFlowerPosition[0] != -1 && closestFlowerPosition[1] != -1) {
            System.out.println("x: " + closestFlowerPosition[0] + "y :" +closestFlowerPosition[1]);
            if(eat){
                Flower flower = (Flower) garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]);
                flower.changeState('d');
                daysWithouEating = 0;
            }
            move(closestFlowerPosition[0], closestFlowerPosition[1]);
        }
        else{
            if(daysWithouEating>3){
                color = Color.black;
                changeState('d');
            }
        }
        turn();
    }  
    
    @Override
    public void move(int row, int column){
        garden.setThing(this.row, this.column, null);
        this.row = row;
        this.column = column;
        garden.setThing(row, column, this);
    }
}
