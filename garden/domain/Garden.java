package domain;
import java.util.*;
/*No olviden adicionar la documentacion*/
public class Garden{
    static public int LENGTH=40;
    private Thing[][] garden;
    private int count = 0;
    public Garden() {
        garden=new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                garden[r][c]=null;
            }
        }
        setThing(0,0,new Water());
        for (int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                setThing(LENGTH-i,LENGTH-j,new Water());
            }
        }
       
        
        someThings();
    }

    public int  getLength(){
        return LENGTH;
    }

    public Thing getThing(int r,int c){
        return garden[r][c];
    }

    public void setThing(int r, int c, Thing e){
        garden[r][c]=e;
    }

    public void someThings(){
        setThing(10,10, new Flower(this, 10,10)); //rose
        setThing(15,15, new Flower(this, 15,15)); //violet
        setThing(16,16, new Carnivorous(this, 16,16)); //venus
        setThing(5,5, new Carnivorous(this, 5,5)); //sundeuos
        setThing(0,38, new Sand(this, 0, 38)); //tatacoa
        setThing(0,39, new Sand(this, 0, 39)); //sahara 
    }
    
    public void ticTac(){
        
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                Thing thing = garden[r][c];
                if(thing != null && thing instanceof Flower && !(thing instanceof Carnivorous)){
                    Flower flower = (Flower) thing;
                    System.out.println("flor time" + flower.getTime() + "count " + count);

                    flower.act();
                }
                else if(thing != null && thing instanceof Carnivorous){
                    Carnivorous carnivorous = (Carnivorous) thing;
                    System.out.println("time" + carnivorous.getTime() + "count " + count);
                    if(carnivorous.getTime() == count){
                        carnivorous.act();
                    }
                    else{
                        carnivorous.setTime(count-1);
                    }
                    
                }
            }
        }
        count++;
    }

}
