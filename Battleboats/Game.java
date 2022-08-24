// Made by Danial Syed, syed0053
import java.util.Scanner;
public class Game {
    private boolean debug;
    private int rounds;
    public static void main(String[] args){
        boolean debug;
        System.out.println("Would you like to play in debug mode? (Enter Y or N)");
        Scanner debugMode = new Scanner(System.in);
        String input1 = debugMode.next();
        if(input1.equals("Y")){
            debug = true;
        }
        else if(input1.equals("N")){
            debug = false;
        }
        else{
            System.out.println("Not a valid input");
        }
        System.out.println("Please enter difficulty: beginner, intermediate, or expert");
        Scanner gameMode = new Scanner(System.in);
        String input2 = gameMode.next();
        if(input2.equals("beginner")){
            int n = 3;
            System.out.println("There is one boat on the board");
            Board grid = new Board(n);
            Game g = new Game();
            g.gameRounds(grid);
        }
        else if(input2.equals("intermediate")){
            int n = 6;
            System.out.println("There are two boats on the board");
            Board grid = new Board(n);
            Game g = new Game();
            g.gameRounds(grid);
        }
        else if(input2.equals("expert")){
            int n = 9;
            System.out.println("There are three boats on the board");
            Board grid = new Board(n);
            Game g = new Game();
            g.gameRounds(grid);
        }
        else{
            System.out.println("Not a valid input");
        }


    }
    public void gameRounds(Board b){
        while(b.checkGame()==false){
            int rounds = 0;
            rounds +=1;
            for(int i = 0; i < 1000; i++) {//need for loop for break statement, so I just made a random one
                if (debug == false) {
                    b.display();
                    break;
                } else if (debug == true) {
                    b.print();
                    break;
                }
            }
            Scanner location = new Scanner(System.in);
            System.out.println("Please enter x and y for the point you would like to attack(format: number1 number2)");
            int x = location.nextInt();
            int y = location.nextInt();
            int result;
            result = b.fire(x,y);
            if(result == 0){
                //skip next turn
                rounds += 1;
            }
            else if(result == 3){
                System.out.println("Boat down!");
            }
        }
        System.out.println("Game over in " + rounds + "turns");
    }
}