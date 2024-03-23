package domain;

public final class Water  implements Thing{
    private Garden garden;
    private int row;
    private int column;
    public Water(Garden garden, int row, int column){
        this.garden=garden;
        this.row=row;
        this.column=column;
        garden.setThing(row,column,(Thing)this);
        garden.numberWaterBlocks++;
    }
    
    public void act(){
    }
}
