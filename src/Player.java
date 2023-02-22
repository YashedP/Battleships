public class Player
{
    public static final int[] SHIP_LENGTHS = {2, 3, 3, 4, 5};
    
    private String name;
    private Grid playerGrid;
    private int shipCount;
    public int shipsRemaining = 17;
    
    
    public Player() {
        playerGrid = new Grid();
        shipCount = 0;
    }
    
    public void chooseShipLocation(int row, int col, int direction) {
        Ship newShip = new Ship(SHIP_LENGTHS[shipCount]);
        newShip.setLocation(row, col);
        newShip.setDirection(direction);
        playerGrid.addShip(newShip);
        shipCount++;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getShipCount() {
        return shipCount;
    }
    
    public void printMyShips() {
        playerGrid.printShips();
    }
    
    public void printOpponentGuesses() {
        playerGrid.printStatus();
    }
    
    public boolean recordOpponentGuess(int row, int col) {
        if(playerGrid.alreadyGuessed(row, col)) { 
            return false;
        }
        if(playerGrid.hasShip(row, col)) {
            playerGrid.markHit(row, col);
            System.out.println("hit a ship!");
            shipsRemaining--;
        }
        else {
            playerGrid.markMiss(row, col);
            System.out.println("missed!");
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