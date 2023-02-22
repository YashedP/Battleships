public class Grid
{
    public static final int NUM_ROWS = 10;
    public static final int NUM_COLS = 10;
    
    private Location[][] grid;
    
    public Grid() {
        grid = new Location[NUM_ROWS][NUM_COLS];
        
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Location();
            }
        }
    }
    
    public void markHit(int row, int col) {
        grid[row][col].markHit();
    }
    
    public void markMiss(int row, int col) {
        grid[row][col].markMiss();
    }
    
    public void setStatus(int row, int col, int status) {
        grid[row][col].setStatus(status);
    }
    
    public int getStatus(int row, int col) {
        return grid[row][col].getStatus();
    }
    
    public boolean alreadyGuessed(int row, int col) {
        return !grid[row][col].isUnguessed();
    }
    
    public void setShip(int row, int col, boolean val) {
        grid[row][col].setShip(val);
    }
    
    public boolean hasShip(int row, int col) {
        return grid[row][col].hasShip();
    }
    
    public Location get(int row, int col) {
        return grid[row][col];
    }
    
    public int numRows() {
        return NUM_ROWS;
    }
    
    public int numCols() {
        return NUM_COLS;
    }
    
    public void printStatus() {
        System.out.print("  ");
        for(int i = 1; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println(grid.length);    
        for(int i = 0; i < grid.length; i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j].checkHit()) {
                    System.out.print("X ");
                }
                else if (grid[i][j].checkMiss()) {
                    System.out.print("O ");
                }
                else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    
    public void printShips() {
        System.out.print("  ");
        for(int i = 1; i < grid.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println(grid.length);    
        for(int i = 0; i < grid.length; i++) {
            System.out.print((char)(65 + i) + " ");
            for(int j = 0; j < grid.length; j++) {
                if(grid[i][j].hasShip()) {
                    System.out.print("X ");
                }
                else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }
    
    public void addShip(Ship s) {
        if(s.getDirection() == Ship.HORIZONTAL) {
            for(int i = 0; i < s.getLength(); i++) {
                setShip(s.getRow(), s.getCol() + i, true);
            }
        }
        else if(s.getDirection() == Ship.VERTICAL) {
            for(int i = 0; i < s.getLength(); i++) {
                setShip(s.getRow() + i, s.getCol(), true);
            }
        }
    }
}