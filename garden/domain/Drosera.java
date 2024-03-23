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
    private int daysWithoutEating;

    /**
     * Constructor for objects of class Drosera
     */
    public Drosera(Garden garden, int row, int column){
        // initialise instance variables
        super(garden, row, column);
        garden.numberOfFlowers--;
        this.garden = garden;
        this.color = color.green;
        nextState=Agent.ALIVE;
        changeState(nextState);
        garden.numberOfDroseras++;
        setTime(garden.time);
        daysWithoutEating = 0;
    }
    
    private int[] findClosestFlowerAliveOrWater(int targetRow, int targetColumn, boolean eat) {
        int[] closestPosition = new int[]{-1, -1};
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < garden.LENGTH; i++) {
            for (int j = 0; j < garden.LENGTH; j++) {
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
        System.out.println("esta viva? " + isAlive() + " y " + this.isAlive());
        if (getTime()==garden.time){
            boolean eat = getTime()%2==0;
            int[] closestFlowerPosition = findClosestFlowerAliveOrWater(row, column, eat);
            if ((closestFlowerPosition[0] != -1 && closestFlowerPosition[1] != -1) && isAlive()) {
                if(eat){
                    if(garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]) instanceof Carnivorous){garden.numberOfCarnivorous--;}
                    else if(garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]) instanceof Flower){garden.numberOfFlowers--;}
                    Flower flower = (Flower) garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]);
                    flower.changeState('d');
                    daysWithoutEating = 0;
                }
                else{
                    daysWithoutEating++;
                    garden.numberWaterBlocks--;
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
    
    public void revive(){
        changeState('a');
        daysWithoutEating = 0;
        color = Color.green;
    }
}
