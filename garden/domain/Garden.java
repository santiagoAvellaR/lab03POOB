package domain;
import java.util.*;
/*No olviden adicionar la documentacion*/
public class Garden{
    static public int LENGTH=40;
    private Thing[][] garden;
    public static int time = 0;
    public static int numberOfFlowers = 0;
    public static int numberOfCarnivorous = 0;
    public static int numberOfDroseras = 0;
    public static int numberWaterBlocks = 0;
    public static int numberSandBlocks = 0;
    public static int numberOfGardeners = 0;
    public Garden() {
        garden=new Thing[LENGTH][LENGTH];
        for (int r=0;r<LENGTH;r++){
            for (int c=0;c<LENGTH;c++){
                garden[r][c]=null;
            }
        }
        new Water(this, 0, 0);
        for (int i=1;i<5;i++){
            for (int j=1;j<5;j++){
                new Water(this,LENGTH-i,LENGTH-j);
            }
        }
        //someThings();
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
        //Flower rose = new Flower(this, 10, 10);
        //Flower violet = new Flower(this, 15, 15);
        //Carnivorous venus = new Carnivorous(this, 16, 16);
        //Carnivorous sundeuos = new Carnivorous(this, 5, 5);
        //Sand tatacoa = new Sand(this, 0, 38);
        //Sand sahara = new Sand(this, 0, 39);
        //Drosera santiago = new Drosera(this, 2  , 20);
        Drosera daniel = new Drosera(this, 20, 20);
        //Gardener nicolas = new Gardener(this, 25, 25);
        //Gardener samuel = new Gardener(this, 22, 22);
    }
    
    public void ticTac(){
        System.out.println("click");
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
