package domain;


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

    @Override
    public void act() {
    // Verifica si hay una flor viva en la vecindad
    int comer = 0;
    for (int r = 0; r <= 39; r++) {
        for (int c = 0; c <= 39; c++) {
            Thing thing = garden.getThing(r, c);
            if (thing instanceof Flower) {
                Flower flower = (Flower) thing;
                System.out.println(flower.isAlive());
                if (flower.nextState == 'a' && comer == 0) {
                    // Come la flor
                    garden.setThing(row, column, this);
                    garden.setThing(flower.getRow(), flower.getColumn(), this);
                    flower.nextState = flower.DEAD;
                    comer = 1;
                }
            }
        }
    }
    }

    
}
    
