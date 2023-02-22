public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private Grid playerGrid;
    private Grid playerGuesses;
    private int shipCount;
    
    public Player() {
        playerGrid = new Grid();
        playerGuesses = new Grid();
        shipCount = 0;
    }
    
    public void chooseShipLocation(int row, int col, int direction) {
        Ship newShip = new Ship(SHIP_LENGTHS[shipCount]);
        newShip.setLocation(row, col);
        newShip.setDirection(direction);
        playerGrid.addShip(newShip);
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
    
    public boolean checkShipLocation(int row, int col, int direction) {
        Ship newShip = new Ship(SHIP_LENGTHS[shipCount]);
        newShip.setLocation(row, col);
        newShip.setDirection(direction);
        return playerGrid.checkShipLocation(newShip);
    }
}