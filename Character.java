import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Character {
	 	private int x, y, width, height, gravity, deaths, frame, jumpLimit;                                                    //First point I used in whole game
	    private BufferedImage[] images;
	    private boolean moveLeft, moveRight, jump;
	    
	    public Character(int x, int y, int w, int h, int gr, int d, int fr, int jl) {
	    	this.x = x;
	    	this.y = y;
	        width = w;
	        height = h;
	        gravity = gr;
	        deaths = d;
	        frame = fr;
	        jumpLimit=jl;
	        images = new BufferedImage[12];
	        try {
	            for (int i = 0; i < images.length; i++) {
	                images[i] = ImageIO.read(new File("src/Wizards/wizard" + i + ".png"));
	            }
	        } catch (IOException e) {
	            System.out.println("NOT LOADED");
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
	    public int getGravity() {
	        return gravity;
	    }
	    public int getDeaths() {
	        return deaths;
	    }
	    public int getFrame() {
	        return frame;
	    }
	    public int getJumpLimit() {
	        return jumpLimit;
	    }
	    public BufferedImage[] getImages() {
	        return images;
	    }
	    
	    public void setX(int x) {
	    	this.x = x;
	    }
	    public void setY(int y) {
	    	this.y = y;
	    }
	    public void setMoveRight(boolean moveRight) {
	        this.moveRight=moveRight;
	    }
	    public void setMoveLeft(boolean moveLeft) {
	        this.moveLeft = moveLeft;
	    }
	    public void setWidth(int width) {
	        this.width = width;
	    }
	    public void setHeight(int height) {
	        this.height = height;
	    }
	    public void setGravity(int gravity) {
	        this.gravity = gravity;
	    }
	    public void setDeaths(int deaths) {
	        this.deaths = deaths;
	    }
	    public void setFrame(int frame) {
	        this.frame = frame;
	    }
	    public void setJumpLimit(int jl) {
	        jumpLimit=jl;
	    }

	    public void draw(Graphics g, GUI gui) {
	    	g.drawImage(images[frame], x, y, width, height, gui);
	        if(moveLeft==true) {
	            frame++;
	            if(frame>11){
	                frame=6;
	            }
	        }
	        if(moveRight==true) {
	            frame++;
	            if(frame>5) {
	                frame=0;
	            }
	        }
	    }
	    public void move() {
	        if(moveLeft==true) {
	            x -= 5;
	        }
	        if(moveRight == true) {
	            x += 5;
	        }
	    }
	    public void print() {
	        System.out.println("Batman Print Info --> Starting Points: (" + x + "," + y + ") width and height: " + width+  ","  + height + " total deaths: " + deaths);
	    }
	    public void gravity() {
	        gravity++;
	        y = y + gravity;
	        
	        if(y>720) {
	        	y = 720;
	        	jumpLimit = 0;
	        	gravity = 0;
//	            y = 250;
//	            x = 60;
	        }
	    }
	    
	    public void collisionPlatfrom(ArrayList<Platforms> plat1) {
	        // x1<=x2+W2 && x2<=x1+W1 && y1<=y2+H2 && y2<=y1+H1
	        for(int i = 0; i < plat1.size(); i++) {
		            if(x+15 <= plat1.get(i).getX() + plat1.get(i).getWidth() && plat1.get(i).getX()<= x + 12 + 40 && y + 125 <= plat1.get(i).getY() + plat1.get(i).getHeight() && plat1.get(i).getY() <= y + 125 + 12) {
		                y = plat1.get(i).getY()-135;
		                jumpLimit=0;
		                gravity=0;
		            }
	        }
	    }
	    
	    public void bigCollisonPlatform(Platforms plat1) {
	    	if(x+15 <= plat1.getX() + plat1.getWidth()*3 && plat1.getX()<= x + 12 + 40 && y + 125 <= plat1.getY() + plat1.getHeight() && plat1.getY() <= y + 125 + 12) {
                y = plat1.getY()-135;
                jumpLimit=0;
                gravity=0;
            }
	    }
	    public void collisionEnemy(Enemy firefly) {
	        if(x <= firefly.getX() + firefly.getWidth() - 10 && firefly.getX() <= x + width-50 && y <= firefly.getY() + firefly.getHeight() && firefly.getY() <= y + height - 50 ) {
	            y = 250;
	            x = 60;
	            deaths++;
	        }
	  
	    }
	    public void collisionGoblin(Enemy goblin) {
	    	if(x <= goblin.getX() + goblin.getWidth() - 10 && goblin.getX() <= x + width-50 && y <= goblin.getY() + goblin.getHeight() && goblin.getY() <= y + height - 50 ) {
	    		y = 200;
	    		x = 60;
	    		deaths++;
	    	}
	    }
	    
	    public void collisionFireball(ArrayList<Fireball> fireballs) {
	    	if(x <= fireballs.get(0).getX() + fireballs.get(0).getWidth() - 10 && fireballs.get(0).getX() <= x + width-50 && y <= fireballs.get(0).getY() + fireballs.get(0).getHeight() && fireballs.get(0).getY() <= y + height - 50) {
	    		fireballs.clear();
	    		y = 700;
	    		x = 0;
	    		deaths++;
	    	}
	    }
	    
	    public void collisionDragon(Enemy dragon) {
	    	if(x <= dragon.getX() + dragon.getWidth() - 10 && dragon.getX() <= x + width-50 && y <= dragon.getY() + dragon.getHeight() && dragon.getY() <= y + height - 50 ) {
	    		y = 700;
	    		x = 0;
	    		deaths++;
	    	}
	    }
}
