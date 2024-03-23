package domain;
import java.util.*;
/*No olviden adicionar la documentacion*/
public class Garden{
    static public int LENGTH=40;
    private Thing[][] garden;
    public static int time = 0;
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
        Flower rose = new Flower(this, 10, 10);
        Flower violet = new Flower(this, 15, 15);
        Carnivorous venus = new Carnivorous(this, 16, 16);
        Carnivorous sundeuos = new Carnivorous(this, 5, 5);
        Sand tatacoa = new Sand(this, 0, 38);
        Sand sahara = new Sand(this, 0, 39);
        Drosera santiago = new Drosera(this, 2  , 20);
        Drosera daniel = new Drosera(this, 20, 20);
        Gardener nicolas = new Gardener(this, 25, 25);
    }
    
    public void ticTac(){
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                Thing thing = garden[r][c];
                if(thing != null){
                    thing.act();
                }
            }
        }
        time++;
    }
}
