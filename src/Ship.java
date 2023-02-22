public class Ship
{
    public static final int UNSET = -1;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    
    private int row;
    private int col;
    private int length;
    private int direction;
    
    public Ship(int length) {
        this.length = length;
        this.row = UNSET;
        this.col = UNSET;
        this.direction = UNSET;
    }
    
    public boolean isLocationSet() {
        if(row == UNSET || col == UNSET) {
            return false;
        }
        return true;
    }
    
    public boolean isDirectionSet() {
        if(direction == UNSET) {
            return false;
        }
        return false;
    }
    
    public void setLocation(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    public void setDirection(int direction) {
        this.direction = direction;
    }
    
    public int getRow() {
        return row;
    }
    
    public int getCol() {
        return col;
    }
    
    public int getLength() {
        return length;
    }
    
    public int getDirection() {
        return direction;
    }
    
    private String directionToString() {
        if(direction == 0) {
            return "horizontal";
        }
        else if(direction == 1) {
            return "vertical";
        }
        return "unset direction";
    }
    
    private String locationToString() {
        if(isLocationSet()) {
            return row + ", " + col;
        }
        return "unset location";
    }
    
    public String toString() {
        return directionToString() + " ship of length " + getLength() + " at (" + locationToString() + ")";
    }
}