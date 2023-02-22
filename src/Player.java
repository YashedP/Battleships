public class Player
{
    private static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Grid playerGrid;
    private Grid playerGuesses;
    private int shipCount;
    
    public Player() {
        playerGrid = new Grid();
        playerGuesses = new Grid();
        shipCount = 0;
    }
    
    public void chooseShipLocation(Ship s, int row, int col, int direction) {
        if(shipCount < 5) {
            s.setLocation(row, col);
            s.setDirection(direction);
            playerGrid.addShip(s);
        }
        shipCount++;
    }
    
    public void printMyShips() {
        playerGrid.printShips();
    }
    
    public void printOpponentGuesses() {
        playerGrid.printStatus();
    }
    
    public void printMyGuesses() {
        playerGuesses.printStatus();
    }
    
    public boolean recordOpponentGuess(int row, int col) {
        if(playerGrid.alreadyGuessed(row, col)) { 
            return false;
        }
        if(playerGrid.hasShip(row, col)) {
            playerGrid.markHit(row, col);
        }
        else {
            playerGrid.markMiss(row, col);
        }
        return true;
    }
}