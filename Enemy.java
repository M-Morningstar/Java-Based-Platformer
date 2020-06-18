import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy {
    private int x, y, width, height, frame;
    private BufferedImage[] imagesT, imagesG, imagesD;
    private boolean moveLeft, moveRight;
    
    public Enemy(int x, int y, int w, int h, int fr) {
        this.x = x;
        this.y = y;
    	width = w;
        height = h;
        frame = fr;
        imagesT = new BufferedImage[16];
        imagesG = new BufferedImage[12];
        imagesD = new BufferedImage[36];
        try {
            for (int i = 0; i < imagesT.length; i++) {
                imagesT[i] = ImageIO.read(new File("src/Trolls/troll" + i + ".png"));
            }
            for(int i = 0; i < imagesG.length; i++) {
            	imagesG[i] = ImageIO.read(new File("src/Goblins/goblin" + i + ".png"));
            }
            for(int i = 0; i < imagesD.length; i++) {
            	imagesD[i] = ImageIO.read(new File("src/Dragon/dragon" + i + ".png"));
            }
        } catch (IOException e) {
            System.out.println("ENEMY NOT LOADED");
        }
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
    public int getFrame() {
        return frame;
    }  
    public boolean isMoveLeft() {
		return moveLeft;
	}
	public boolean isMoveRight() {
		return moveRight;
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
    public void setFrame(int fr) {
        this.frame = fr;
    }
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }
    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void drawT(Graphics g, GUI gui) {
        g.drawImage(imagesT[frame], x, y, width, height, gui);
        if(moveLeft==true) {
            frame++;
            if(frame>15){
                frame=8;
            }
        }
        if(moveRight==true) {
            frame++;
            if(frame>7) {
                frame=0;
            }
        }
    }
    
    public void drawD(Graphics g, GUI gui) {
        g.drawImage(imagesD[frame], x, y, width, height, gui);
        if(moveLeft==true) {
            frame++;
            if(frame>35){
                frame=18;
            }
        }
        if(moveRight==true) {
            frame++;
            if(frame>17) {
                frame=0;
            }
        }
    }
    
    public void drawG(Graphics g, GUI gui) {
        g.drawImage(imagesG[frame], x, y, width, height, gui);
        if(moveLeft==true) {
            frame++;
            if(frame>11){
                frame=7;
            }
        }
        if(moveRight==true) {
            frame++;
            if(frame>5) {
                frame=1;
            }
        }
    }
    
    public void print() {
        System.out.println("Enemy Print Info --> X, Y, width, height, direction: " + x + "," + y + ", " + width +  height + frame );
    }
    
	public void moveFast() {
		if(moveLeft == true) {
            x -= 7;
        }
        if(moveRight == true) {
            x += 7;
        }
	}
	public void moveSlow() {
		if(moveLeft == true) {
            x -= 5;
        }
        if(moveRight == true) {
            x += 5;
        }
	}
	public void moveDragon() {
		if(moveLeft == true) {
            x -= 10;
        }
        if(moveRight == true) {
            x += 10;
        }
	}
	


}













