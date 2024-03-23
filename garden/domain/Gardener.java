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
        garden.numberOfGardeners++;
        setTime(garden.time);
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
        garden.setThing(row, column, null);
        this.row = row;
        this.column = column;
        garden.setThing(row, column, this);
    }
    
    private int selectTheAgentWithMinimunNumber(){
        return Math.min(Math.min(garden.numberOfFlowers, garden.numberOfCarnivorous), Math.min(garden.numberSandBlocks, garden.numberWaterBlocks));
    }
    
    private int[] searchTheClosestTwoAdyacentEmptySpaces(int targetRow, int targetColumn){
        int[] closestPosition = new int[]{-1, -1, -1, -1};
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < garden.LENGTH; i++) {
            for (int j = 0; j < garden.LENGTH; j++) {
                if ( (garden.getThing(i, j) == null )) {
                    int distance = Math.abs(targetRow - i) + Math.abs(targetColumn - j);
                    if (distance < minDistance) {
                        minDistance = distance;
                        for(int k = i-1;k  < i+2; k++){
                            for(int l = j-1;l  < j+2; l++){
                                if( k >= 0 && k < garden.LENGTH && l >=0 && l < garden.LENGTH &&  k != i && l!= j){
                                    if (garden.getThing(k, l) == null ){
                                        closestPosition[0] = i; 
                                        closestPosition[1] = j;
                                        closestPosition[2] = k;
                                        closestPosition[3] = l;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return closestPosition;
    }
    
    private int[] findClosestDroseraDead(int targetRow, int targetColumn) {
        int[] closestPosition = new int[]{-1, -1};
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < garden.LENGTH; i++) {
            for (int j = 0; j < garden.LENGTH; j++) {
                if (garden.getThing(i, j) instanceof Drosera) {
                    Drosera drosera = (Drosera) garden.getThing(i, j);
                    if (!drosera.isAlive()) {
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
    
    public void act(){
        if(getTime() == garden.time){
            if (selectTheAgentWithMinimunNumber() > 8){
                int[] closestDroseraDead = findClosestDroseraDead(row, column);
                if(closestDroseraDead[0] !=-1 && closestDroseraDead[1]!=-1){
                    Drosera drosera = (Drosera) garden.getThing(closestDroseraDead[0], closestDroseraDead[1]);
                    drosera.revive();
                }
            }
            else{
                int[] twoEmptySpaces = searchTheClosestTwoAdyacentEmptySpaces(row, column);
                if(twoEmptySpaces[0]!=-1 && twoEmptySpaces[1]!=-1 && twoEmptySpaces[2]!=-1 && twoEmptySpaces[3]!=-1){
                    if(selectTheAgentWithMinimunNumber() == garden.numberOfFlowers){
                        new Flower(garden, twoEmptySpaces[2], twoEmptySpaces[3]);
                    }
                    else if(selectTheAgentWithMinimunNumber() == garden.numberOfCarnivorous){
                        new Carnivorous(garden, twoEmptySpaces[2], twoEmptySpaces[3]);
                    }
                    else if(selectTheAgentWithMinimunNumber() == garden.numberSandBlocks){
                        new Sand(garden, twoEmptySpaces[2], twoEmptySpaces[3]);
                    }
                    else{
                        new Water(garden, twoEmptySpaces[2], twoEmptySpaces[3]);
                    }
                    move(twoEmptySpaces[0], twoEmptySpaces[1]);
                }
            }
            turn();
        }
    }
}
