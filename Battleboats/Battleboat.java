//made by Danial Syed, syed0053
import java.util.Scanner;

public class Battleboat {
    private int size;
    private boolean orientation;
    private Cell[] spaces;
    public Battleboat(int length){
        int x;
        size = length;
        //randomly decides if orientation is 1 or 2, horizontal or vertical respectively
        x = (int)Math.floor(Math.random()*2-0.1);
        if(x==1){
            orientation = true;//vertical
        }
        else if(x==0){
            orientation = false;//horizontal
        }
    }
    public boolean getOrientation(){
        return orientation;
    }
    public void setSpaces(Cell[] cells){
        spaces = cells;
    }
    public Cell[] getSpaces(){
        return spaces;
    }
    public int getSize(){
        return size;
    }
}
