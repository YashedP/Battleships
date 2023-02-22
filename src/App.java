import java.util.Scanner;

public class App {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Battleships! What mode would you like to play?");
        System.out.println("Mode 1: player vs. player");
        System.out.println("Mode 2: player vs. computer");
        int mode = input.nextInt();
        input.nextLine();
        Battleship.clearConsole();
        
        Battleship game = new Battleship(mode);
        input.close();
    }
}