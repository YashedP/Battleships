import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Battleships! What mode would you like to play?");
        boolean validAnswer = true;
        int mode = -1;
        while(validAnswer) {
            System.out.println("Mode 1: player vs. player");
            System.out.println("Mode 2: player vs. computer");
            
            try { 
                mode = input.nextInt();
            }
            catch(InputMismatchException e) {
                System.out.println("Please enter a valid number!");
                input.nextLine();
            }
            
            if(mode >= 1 && mode <= 2) {
                validAnswer = false;
            }
            else {
                System.out.println("Invalid answer, please repeat yourself\n");
            }
        }
        
        input.nextLine();
        Battleship.clearConsole();
        
        Battleship game = new Battleship(mode);
        input.close();
    }
}