package domain;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Write a description of class Carnivorous here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carnivorous extends Flower{
    // instance variables - replace the example below with your own
    private Garden garden;
    private int contadorTic;
    
    public Carnivorous(Garden garden, int row, int column){
         super(garden, row, column);
         this.garden = garden;
         this.color = color.blue;
         
    }
    
    private int[] findClosestAliveFlower(int targetRow, int targetColumn) {
        int[] closestPosition = new int[]{-1, -1};
        int minDistance = Integer.MAX_VALUE;
    
        for (int i = 0; i < 39; i++) {
            for (int j = 0; j < 39; j++) {
                if ( garden.getThing(i, j) instanceof Flower && !(garden.getThing(i, j) instanceof Carnivorous)) {
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
            }
        }
    
        return closestPosition;
    }
    
    @Override
    public void act() {
        
        int[] closestFlowerPosition = findClosestAliveFlower(row, column);
        if (closestFlowerPosition[0] != -1 && closestFlowerPosition[1] != -1) {
            System.out.println("x: " + closestFlowerPosition[0] + "y :" +closestFlowerPosition[1]);
            Flower flower = (Flower) garden.getThing(closestFlowerPosition[0], closestFlowerPosition[1]);
            flower.changeState('d');
            garden.setThing(row, column, null);
            row = closestFlowerPosition[0];
            column = closestFlowerPosition[1];
            garden.setThing(closestFlowerPosition[0], closestFlowerPosition[1], this);
        }
        turn();
    }  
}
    
