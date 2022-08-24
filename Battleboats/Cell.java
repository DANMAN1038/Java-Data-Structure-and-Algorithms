//made by Danial Syed, syed0053
import java.util.Scanner;
public class Cell {
    private int row;
    private int col;
    //status means the state of a cell given coordinates(row and col)
    //key: "_" = has not been guessed, no boat present
    //     "B" = has not been guessed, boat present
    //     "H" = has been guessed, boat present
    //     "M" = has been guessed, no boat present
    private char status;
    public Cell(int row, int col, char status){
        this.row = row;
        this.col = col;
        this.status = status;
    }
    public char getStatus(){
        return status;
    }
    public void setStatus(char c){
        this.status = c;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
}

