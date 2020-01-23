package GrainGrowth;

/**
 * 
 * @author marcinkrzyzowski
 */
public final class Point {
    
    /// Id of the point
    private int id;
    
    /// x coordinate
    private int x;
    
    /// y coordinate
    private int y;
    
    /// value of the point
    private int value;
    
    /// Initializes Point with default values
    public Point(){
        this.x = 0;
        this.y = 0;
        this.value = 0;
    }
    
    /// Initializes Point with given parameters and id
    public Point(int x, int y, int value, int id){
        this.x = x;
        this.y = y;
        this.value = value;
        this.id = id;
    }
    
    /// Initializes Point with given parameters
    public Point(int x, int y, int value){
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public int getId(){
        return id;
    }
    
    public void increaseValue(){
        value++;
    }
    
    public boolean point2point(int x, int y,int r){
        return Math.sqrt(Math.pow(this.x - x,2)+Math.pow(this.x - x,2))>r ;
    }
}