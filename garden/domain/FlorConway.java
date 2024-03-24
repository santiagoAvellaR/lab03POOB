package domain;


/**
 * Write a description of class FlorConway here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlorConway extends Flower
{
    // instance variables - replace the example below with your own
    private Garden garden;
    
     /**
     * Constructor for objects of class Carnivorous.
     *
     * @param garden The garden where the carnivorous plant is located.
     * @param row The row position of the carnivorous plant.
     * @param column The column position of the carnivorous plant.
     */
    public FlorConway(Garden garden, int row, int column){
         super(garden, row, column);
         garden.numberOfFlowers--;
         this.garden = garden;
         this.color = color.blue;
         nextState = Agent.ALIVE;
         changeState(nextState);
         setTime(garden.time);
    }
    
    public void act(){
        turn();
        changeState(nextState);
        int celulasvivas = 0;
        int celulasmuertas = 0;
        for(int i = row-1; i < row + 2; i++)
        {
            for(int j = row-1; j < row + 2; j++)
            {
                if( i >= 0 && i < garden.LENGTH && j >=0 && j < garden.LENGTH)
                {
                    if(garden.getThing(i, j) instanceof Celula)
                    {
                        Celula celula = (Celula)garden.getThing(i,j);
                        if(celula.isAlive()){
                            celulasvivas++;
                        }
                        else
                        {
                            celulasmuertas++;
                        }
                    }
                }
            }   
        }
        
    }
    public void buscarCeldavacia(int x, int y){
        int[] closestPosition = new int[]{-1, -1};
        
    }
    public void dieOrRelive(int vivas, int muertas){
        if(vivas < 2 || vivas > 3){
            nextState = Agent.DEAD;
        }
        else if(nextState == 'd' && vivas == 3)
        {
            nextState = Agent.ALIVE;
             buscarCeldavacia(row, column);
        }
        
    }
   
}