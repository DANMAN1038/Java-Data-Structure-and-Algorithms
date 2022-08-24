//made by Danial Syed, syed0053
import java.util.Scanner;
    public class Board {
        private int numBoats;
        private Battleboat[] boats;
        private Cell[][] board; // revealed board
        private Cell[][] hideBoard; // board with hidden locations of boats
        private int[] boatSizes;
        private int size;
        private int width; // same as length, just needed for this class for printing and debugging
        public Board(int mode){
            width = mode; // this is used to store mode and use in other methods
            board = new Cell[mode][mode];
            for(int i = 0; i < mode; i++){
                for(int j = 0; j < mode; j++){
                    //turns all cells in '_' to start
                    board[i][j] = new Cell(i, j, '_');
                }
            }
            hideBoard = board;

            if(mode == 3){
                numBoats = 1;
                boatSizes = new int[]{2};
                boats = new Battleboat[numBoats];
            }
            else if(mode == 6){
                numBoats = 3;
                boatSizes = new int[]{2,3,4};
                boats = new Battleboat[numBoats];
            }
            else if(mode == 9){
                numBoats = 5;
                boatSizes = new int[]{2,3,3,4,5};
                boats = new Battleboat[numBoats];
            }

        }

        //initially places boats on board
        public void placeBoats(){
            Battleboat a = new Battleboat(width);
            boolean inbounds, layout, placement;
            int xBoat, yBoat;
            layout = a.getOrientation();
            xBoat = (int)Math.floor(Math.random()*width);
            yBoat = (int)Math.floor(Math.random()*width);
            inbounds = bounds(xBoat,yBoat,layout);
            placement = checkBoat(xBoat, yBoat);
            for(int i = 0; i < numBoats; i++){
                if(inbounds==true && placement == true){
                    board[xBoat][yBoat].setStatus('B');
                }
                else{
                    placeBoats();
                }
            }
        }
        //locates where player wants to attack
        public int fire(int x, int y) {
            //penalty == 0
            //Miss == 1
            //Hit == 2
            if (board[x-1][y-1].equals('_')) {
                board[x-1][y-1].setStatus('M'); //update both boards with known attacks
                hideBoard[x][y].setStatus('M');
                System.out.println("Miss");
                return 1;
            } else if (board[x-1][y-1].equals('B')) {
                board[x-1][y-1].setStatus('H');
                hideBoard[x-1][y-1].setStatus('H');
                boolean result;
                result = boatSunk(x,y);
                if(result==true)
                System.out.println("Hit");
                return 2;
            } else {
                System.out.println("Penalty, -1");
                return 0;
            }
        }
        //prints state of board before each turn
        public void display(){
            System.out.println(hideBoard);
        }
        //prints revealed board when in debug mode
        public void print(){
            for(int i = 0; i < width; i++){
                for(int j = 0; j < width; j++){
                    System.out.println(board[i][j]);
                }
            }

        }
        //checks to see if attacking is in bounds
        public boolean bounds(int x, int y, boolean orientation){
            if(y >= width-1 || x >= width-1 || x < 0 || y < 0){
                return true;}
            else{
                return false;}
        }
        //checks to see if number of boats that we began with is == to the number of sunken boats, this decides if the game ends
        public boolean checkGame(){
            if(numBoats == 0){
                return true;
            }
            else{
                return false;
            }
        }
        public boolean checkBoat(int x, int y){//checks to see if there is already a boat on a given spot
            if(board[x][y].equals('B')){
                return false;
            }
            else{
                return true;
            }
        }
        public boolean boatSunk(int x, int y){
            int currentSunkSize = 0;//holds line of coordinates
            for(Battleboat currBoat: boats){
                for(Cell currCell: currBoat.getSpaces()){
                    if(currCell.getStatus()=='H'){
                        currentSunkSize+=1;
                    }
                }
                if(currentSunkSize == currBoat.getSize()){
                    numBoats-=1;
                }
            }
            if(checkGame()==true){
                return true;
            }
            else{
                return false;
            }
        }
    }


