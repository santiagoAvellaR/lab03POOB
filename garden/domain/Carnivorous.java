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
    
    private int[] searchTheClosestFlowerAlive(){
        int[] coordinates = {100, 100};
        int radio = 1;
        while(!(radio + column < 40 || radio - column >= 0 || radio + row < 40 || radio - row >= 0) && coordinates[0] == 100){
            for(int i = row-radio; i <= row+radio; i++){
                for(int j = row-radio; j <= row+radio; j++){
                    try {
                        Thing thing = garden.getThing(i, j);
                        if(thing instanceof Flower && !(thing instanceof Carnivorous)){
                            Flower flower = (Flower) thing;
                            if(flower.isAlive()){
                                coordinates[0] = i;
                                coordinates[0] = j;
                                break;
                            }
                        }
                    }
                    catch (Exception e){}
                }
            }
            radio++;
        }
        return coordinates;
    }
    
    @Override
    public void act() {
        // Verifica si hay una flor viva en la vecindad
        int[] coordinates = searchTheClosestFlowerAlive();
        if(coordinates[0] != 100){
            Thing thing = garden.getThing(coordinates[0], coordinates[1]);
            Flower flower= (Flower) thing;
            flower.changeState('d');
            garden.setThing(row, column, null);
            row = coordinates[0];
            column = coordinates[1];
            garden.setThing(coordinates[0], coordinates[1],this);
        }
    }

    
}
    
