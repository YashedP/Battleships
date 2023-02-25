import java.util.InputMismatchException;

// Advance the AI so that if it hit's a ship then it tries going the squares around the ship
// Idea: switch the next move after hitting a ship, row-1, row+1, column-1, column+1 and switch
// Idea: to make turns more efficient, have a player method take the other player as a paramter and use that to record the guess, refer to pikachu pokemon attack methd

public class Battleship
{
    private int mode;
    private Player player1;
    private Player player2;
    private int rowGuess;
    private int colGuess;
    private int[][][] computerQueue;
    
    public Battleship(int mode) {
        this.mode = mode;
        
        if(mode == 1) {
            System.out.println("Player 1");
        }
        
        player1 = setupUser(player1);
        if(mode == 1) {
            System.out.println("Player 2");
            player2 = setupUser(player2);
        }
        else {
            player2 = setupComputer(player2);
        }
        
        playGame();
        
        System.out.println();
        if(mode == 1) {
            if(player1.shipsRemaining == 0) {
                System.out.println("Congratulations!! " + player1.getName() + " won!!");
                System.out.println(player2.getName() + "'s ships");
                player2.printMyShips();
            }
            else {
                System.out.println("Congratulations!! " + player2.getName() + " won!!");
                System.out.println(player1.getName() + "'s ships");
                player1.printMyShips();
            }
        }
        else {
            if(player2.shipsRemaining == 0) {
                System.out.println("Congratulations!! You won!!");
                System.out.println("Your ships");
                player1.printMyShips();
            }
            else {
                System.out.println("You lost the game :/");
                System.out.println("Your opponent's ships");
                player2.printMyShips();
            }
        }
    }
    
    public Player setupUser(Player player) {
        player = new Player();
        
        if(mode == 1) {
            System.out.println("Enter your name");
            String name = App.input.nextLine();
            player.setName(name);
        }
        
        chooseShipLocation(player);
        clearConsole();
        
        return player;
    }
    
    public Player setupComputer(Player computer) {
        computer = new Player();
        for(int i = 0; i < 5; i++) {
            randomizeShipLocation(computer);
        }
        
        computerQueue = new int[10][10][2];

        for(int i = 0; i < computerQueue.length; i++) {
            for(int j = 0; j < computerQueue[i].length; j++) {
                computerQueue[i][j][0] = i;
                computerQueue[i][j][1] = j;
            }
        }

        shuffle(computerQueue);
        
        return computer;
    }
    
    public void playGame() {
        System.out.println("Now let's play the game!\n");

        int i = 0;
        int j = 0;

        boolean win = false;
        while(!win) {
            if(mode == 2) {
                System.out.print("It is your turn");
            } 
            else {
                System.out.print("It is " + player1.getName() + "'s turn");
            }
            System.out.println(", press enter when ready.");
            App.input.nextLine();
            
            player2.printOpponentGuesses();
            
            boolean guess = false;
            while(!guess) {
                recordGuess();
                System.out.print("\nYou ");
                if(player2.recordOpponentGuess(rowGuess, colGuess)) {
                    guess = true;
                }
                else {
                    System.out.println("Invalid guess, try again");
                }
            }
            player2.printOpponentGuesses();

            if(mode == 2) {
                System.out.println("It is the computer's turn, press enter when ready");
                App.input.nextLine();
                player1.printOpponentGuesses();
                
                System.out.println("Press enter to let the computer guess");
                App.input.nextLine();
                System.out.print("The computer ");
                player1.recordOpponentGuess(computerQueue[i][j][0], computerQueue[i][j][1]);
                
                j++;
                if(j == 10) {
                    i++;
                    j = 0;
                }
                
                player1.printOpponentGuesses();
            }
            else {
                System.out.print("It is " + player2.getName() + "'s turn");
                System.out.println(", press enter when ready.");
                App.input.nextLine();
                
                player1.printOpponentGuesses();
                
                guess = false;
                while(!guess) {
                    recordGuess();
                    System.out.print("\nYou ");
                    if(player1.recordOpponentGuess(rowGuess, colGuess)) {
                        guess = true;
                    }
                    else {
                        System.out.println("Invalid guess, try again");
                    }
                }
                player1.printOpponentGuesses();
            }
            
            if(player1.shipsRemaining == 0 || player2.shipsRemaining == 0) {
                win = true;
            }
        }
    }

    public void recordGuess() {
        rowGuess = -1;
        while(rowGuess == -1) {
            System.out.println("What row would you like to place your guess? Choose a row between A-J");
            char charRow;
            charRow = App.input.nextLine().toUpperCase().charAt(0);
            rowGuess = ((int)charRow) - 65;
            
            boolean check = true;
            if(rowGuess < 0 || rowGuess > 9) {
                check = false;
            }

            if(!check) {
                System.out.println("Your answer was not understandable, can you repeat yourself?");
                rowGuess = -1;
            }
        }

        colGuess = -1;
        while(colGuess == -1) {
            System.out.println("What column would you like to place your guess? Choose a row between 1-10");
            try {
                colGuess = App.input.nextInt();
                colGuess -= 1;

                boolean check = true;
                if(colGuess < 0 || colGuess > 9) {
                    check = false;
                }
    
                if(!check) {
                    System.out.println("Your answer was not understandable, can you repeat yourself?");
                    colGuess = -1;
                }    
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter a valid number!");
            }
        }
        App.input.nextLine();
    }
    
    private int row;
    private int col;
    private int direction;

    public void chooseShipLocation(Player player) {
        System.out.println("\nWe will begin by chossing the location of your ships!\n");
        player.printMyShips();
        for(int i = 0; i < 5; i++) {
            row = -1;
            col = -1;
            direction = -1;
            
            System.out.println("Ship #" + (i + 1) + " consists of " + Player.SHIP_LENGTHS[i] + " length");
            askForShipLocation(player, Player.SHIP_LENGTHS[i]);

            player.chooseShipLocation(row, col, direction);
            player.printMyShips();
        }
    }
    
    public void randomizeShipLocation(Player player) {
        int length = Player.SHIP_LENGTHS[player.getShipCount()];
        int direction = Randomizer.nextInt(2);
        int row;
        int col;
        
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
            String dir = App.input.nextLine().toLowerCase();
            if(dir.equals("horizontal") || dir.equals("h")) {
                direction = 0;
            }
            else if(dir.equals("vertical") || dir.equals("v")) {
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
            charRow = App.input.nextLine().toUpperCase().charAt(0);
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
            col = App.input.nextInt() - 1;
            App.input.nextLine();
            
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
    
    public static void shuffle(int[][][] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array[i].length; j++) {
                int m = (int)(Math.random() * (10));
                int n = (int)(Math.random() * (10));

                int[] temp = array[m][n];
                array[m][n] = array[i][j];
                array[i][j] = temp;
            }
        }
    }
    
    public static void clearConsole() {
        for(int i = 0; i < 60; i++) {
            System.out.println();
        }
    }
}