// Add randomizing the computer's ship placements
// adding that I can use a uppercase or lowercase of vertical, horizontal, A - J

import java.util.Scanner;

public class Battleship
{
    private static Scanner input = new Scanner(System.in);
    private Player player1;
    private Player player2;

    public Battleship(int mode) {
        // setupUser(player1);
        if(mode == 1) {
            setupUser(player2);
        }
        else {
            setupComputer(player2);
        }
        
        // playGame();
    }
    
    public void setupUser(Player player) {
        player = new Player();
        chooseShipLocation(player);
    }
    
    public void setupComputer(Player computer) {
        computer = new Player();
        for(int i = 0; i < 5; i++) {
            randomizeShipLocation(computer);
        }
        computer.printMyShips();
    }
    
    private int row;
    private int col;
    private int direction;

    public void chooseShipLocation(Player player) {
        System.out.println("We will begin by chossing the location of your ships!\n");
        player.printMyShips();
        for(int i = 0; i < 5; i++) {
            row = -1;
            col = -1;
            direction = -1;
            
            System.out.println("Ship #" + (i + 1) + " consists of " + Player.SHIP_LENGTHS[i] + " length");
            askForShipLocation(player, Player.SHIP_LENGTHS[i]);

            player.chooseShipLocation(row, col, direction);
        }
    }
    
    public void randomizeShipLocation(Player player) {
        int length = Player.SHIP_LENGTHS[player.getShipCount()];
        int direction = Randomizer.nextInt(2);
        int row;
        int col;
        
        // direction = 0 means horizontal
        // direction = 1 means vertical
        if(direction == 0) {
            row = Randomizer.nextInt(10);
            col = Randomizer.nextInt(11 - length);
        }
        else {
            row = Randomizer.nextInt(11 - length);
            col = Randomizer.nextInt(10);
        }

        if(!player.checkShipLocation(row, col, direction)) {
            randomizeShipLocation(player);
        }
        else {
            player.chooseShipLocation(row, col, direction);
        }
    }
    
    public void askForShipLocation(Player player, int length) {
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
                System.out.println(11 - length);
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
        
        if(!player.checkShipLocation(row, col, direction)) {
            row = -1;
            col = -1;
            direction = -1;
            
            System.out.println("Invalid placement, retry again\n");
            askForShipLocation(player, length);
        }
    }
    
    // public static void askForGuess() {
    //     System.out.println("Enter a valid row and column");
    // }
}