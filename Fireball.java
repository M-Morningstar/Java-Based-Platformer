import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fireball {
	private int x, y, width, height, frame;
	private BufferedImage[] images;
	private boolean moveLeft, moveRight;
	
	public Fireball(int x, int y, int w, int h, int fr) {
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		frame = fr;
		
		images = new BufferedImage[14];
		
		try {
            for (int i = 0; i < images.length; i++) {
                images[i] = ImageIO.read(new File("src/Fireball/fireball" + i + ".png"));
            }
        } catch (IOException e) {
            System.out.println("FIREBALL NOT LOADED");
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
	public void setFrame(int frame) {
		this.frame = frame;
	}
	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}
	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}	
	
	public void draw(Graphics g, GUI gui) {
		g.drawImage(images[frame], x, y, width, height, gui);
		if(moveLeft==true) {
            frame++;
            if(frame>13){
                frame=13;
            }
        }
        if(moveRight==true) {
            frame++;
            if(frame>6) {
                frame=6;
            }
        }
	}
	public void move() {
		if(moveLeft == true) {
            x -= 15;
        }
        if(moveRight == true) {
            x += 15;
        }
	}
}
