// Add randomizing the computer's ship placements
// adding that I can use a uppercase or lowercase of vertical, horizontal, A - J

import java.util.Scanner;

public class Battleship
{
    private static Scanner input = new Scanner(System.in);
    private static int row;
    private static int col;
    private static int direction;
    private static Player user = new Player();
    private static Player computer = new Player();
    
    public static void main(String[] args){
        System.out.println("Welcome to Battleship!");
        
        
        // randomize computer's ships
        System.out.println("We will begin by chossing the location of your ships!\n");
        user.printMyShips();
        for(int i = 0; i < 5; i++) {
            row = -1;
            col = -1;
            direction = -1;
            
            System.out.println("Ship #" + (i + 1) + " consists of " + Player.SHIP_LENGTHS[i] + " length");
            askForShipLocation(Player.SHIP_LENGTHS[i]);
            boolean check = false;
            user.chooseShipLocation(row, col, direction);
            user.printMyShips();
        }
        
        
    }
    
    public static void askForGuess() {
        System.out.println("Enter a valid row and column");
    }
    
    public static void askForShipLocation(int length) {
        while(direction == -1) {
            System.out.println("What direction would you like to place your ship? horizontal or vertical? ");
            String dir = input.nextLine();
            if(dir.equals("horizontal")) {
                direction = 0;
            }
            else if(dir.equals("vertical")) {
                direction = 1;
            }
            else {
                System.out.println("Your answer was not understandable, can you repeat yourself?");
            }
        }
        
        while(row == -1) {
            System.out.print("What row would you like to place your ship? Choose a row between A-");
            char charRow;
            
            if(direction == 1) {
                System.out.println((char)(10 - length + 65));
            }
            else {
                System.out.println("J");
            }
            charRow = input.nextLine().charAt(0);
            row = ((int)charRow) - 65;
            
            boolean check = true;
            if(row < 0) {
                check = false;
            }
            if(direction == 1 && row > 10 - length) {
                check = false;
            }
            if(row > 10) {
                check = false;
            }

            if(!check) {
                System.out.println("Your answer was not understandable, can you repeat yourself?");
                row = -1;
            }
        }
        
        while(col == -1) {
            System.out.print("What column would you like to place your ship? Choose a column between 1-");

            if(direction == 0) {
                System.out.println(10 - length + 1);
            }
            else {
                System.out.println("10");
            }
            col = input.nextInt() - 1;
            input.nextLine();
            
            boolean check = true;
            if(col < 0) {
                check = false;
            }
            if(direction == 0 && col > 10 - length) {
                check = false;
            }
            if(col > 10) {
                check = false;
            }
            
            if(!check) {
                System.out.println("Your answer was not understandable, can you repeat yourself?");
                col = -1;
            }
        }
        
        if(!user.checkShipLocation(row, col, direction)) {
            row = -1;
            col = -1;
            direction = -1;
            
            System.out.println("Invalid placement, retry again\n");
            askForShipLocation(length);
        }
    }
}