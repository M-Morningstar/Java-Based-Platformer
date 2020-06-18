 
import java.awt.Color;
import java.awt.Graphics;

public class Platforms {                                   
    private int x, y, width, height;
    
    private boolean up, down, left, right;
    
    public Platforms(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        width = w;
        height= h;
        
    }
    public int getX() {
    	return x;
    }
    public int getY() {
    	return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    
    
    public void setX(int x) {
    	this.x = x;
    }
    public void setY(int y) {
    	this.y = y;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setUp(boolean up) {
    	this.up = up;
    }
    public void setDown(boolean down) {
    	this.down = down;
    }
    public void setLeft(boolean left) {
    	this.left = left;
    }
    public void setRight(boolean right) {
    	this.right = right;
    }

    public void draw(Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
    public void bigDraw(Graphics g, Color color) {
    	g.setColor(color);
    	g.fillRect(x, y, width*3, height);
    }
    public void print() {
        System.out.println("Platforms Print Info --> X, Y, width, height and direction: " + x + "," + y + ", " + width + ", " +  height);
    }

    public void move() {
    	if(up == true) {
            y -= 5;
        }
        if(down == true) {
            y += 5;
        }
    }
//    public void moveLR() {
//        if(left == true) {
//        	System.out.println("LEFT");
//            x -= 5;
//        }
//        else{
//        	System.out.println("RIGHT");
//            x += 5;
//        }
//    }
    
}












