import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class GUI extends JPanel implements ActionListener {
	int[] dragonYs = {100, 360, 620};
	Random r = new Random();
	int n;
    Character gandalf = new Character(60, 0, 100, 140, 150, 0, 0, 0);
    Enemy troll1 = new Enemy(435, 700, 200, 150, 8);
    Enemy troll2 = new Enemy(645, 700, 200, 150, 0);
    Enemy goblin1 = new Enemy(810, 200, 75, 100, 0);
    Enemy goblin2 = new Enemy(995, 450, 75, 100, 8);
    Enemy goblin3 = new Enemy(565, 750, 75, 100, 0);
    Enemy goblin4 = new Enemy(640, 750, 75, 100, 8);
    Enemy dragon = new Enemy(1580, 200, 250, 200, 18);
    ArrayList<Fireball> fireballs; 
    private int[] polygonX = {300, 315, 330};
    private int[] polygonY = {0, 250, 0};
    Color forestGreen = new Color(34,139,34);
    Color start = new Color(0, 117, 43);
    ArrayList<Integer> platformX = new ArrayList<Integer>();
    ArrayList<Integer> platformY = new ArrayList<Integer>();
    ArrayList<Platforms> platforms = new ArrayList<Platforms>();
    Timer t;
    
    private BufferedImage img, goal, background1, background2, background3, gold, jewel, theEnd, theShire, gameOver;             //img=gandalf symbol for the signal light, gandalfEnd=the gandalf symbol with a writing in it "The End You Are A Hero",background1 is the background for the game itself, background2= is the background for the ending
    private boolean down = true;         
    private boolean level0 = true;
    private boolean level1 = true;
    private boolean level2, level3;												//    private boolean leftE = true;                                				//Enemy left movement
    private boolean win = false;                                				//If this is true game ends
    private boolean drawDeath = true;    
    Font arial = new Font("Rockwell", 2, 40);
    private int level = 0;
    private int goalX = 1150;
    private int goalY = 165;
    private int goalW = 65;
    private int goalH = 32;
    private String text = "Press 'Enter' to start your journey!";

    public GUI() {
    	if(level1 == true) {
    		troll1.setMoveLeft(true);
		    troll2.setMoveRight(true);
	        platformX.add(75);
	        platformX.add(270);
	        platformX.add(510);
	        platformX.add(925);
	        platformX.add(1125);
	
	        platformY.add(320);
	        platformY.add(565);
	        platformY.add(200);
	        platformY.add(550);
	        platformY.add(280);
	        for (int i = 0; i < platformX.size(); i++) {
	            platforms.add(new Platforms(platformX.get(i), platformY.get(i), 125, 20));
	        }
    	}
        initComponents();
        t = new Timer(50, this);
        t.start();
        try {
            background1 = ImageIO.read(new File("src/background.png"));
            background2 = ImageIO.read(new File("src/background2.png"));
            background3 = ImageIO.read(new File("src/background3.png"));
            goal = ImageIO.read(new File("src/arkenstone.png"));
            gold = ImageIO.read(new File("src/gold.png"));
            jewel = ImageIO.read(new File("src/jewel.png"));
            theEnd = ImageIO.read(new File("src/theEnd.png"));
            gameOver = ImageIO.read(new File("src/gameOver.png"));
            theShire = ImageIO.read(new File("src/theShire.png"));
        } catch (IOException e) {
            System.out.println("NOT LOADED");
        }
        fireballs = new ArrayList<Fireball>();
    }
    
    /// Paints background
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(level1 == true) {
	        if(level0 ==false) {
	        
        	g.drawImage(background1, 0, 0, 1280, 900, this);
	        gandalf.draw(g, this);
	        troll1.drawT(g, this);        
	        troll2.drawT(g, this);
	        for (int i = 0; i < platforms.size(); i++) {
	            platforms.get(i).draw(g, forestGreen);
	        }
	        losing(g);
	        }
        	if(level0 == true) {
        		g.setFont(arial);
        		g.setColor(start);
        		g.drawImage(theShire, 0, 0, 1280, 900, this);
        		g.drawString(text, 350, 360);
        	}
        }
        else if(level2 == true) {
        	g.drawImage(background2, 0, 0, 1280, 900, this);
        	gandalf.draw(g, this);
        	goblin1.drawG(g, this);
        	goblin2.drawG(g, this);
        	goblin3.drawG(g, this);
        	goblin4.drawG(g, this);
        	g.setColor(Color.gray);
        	g.fillPolygon(polygonX, polygonY, 3);
        	for (int i = 0; i < platforms.size(); i++) {
        		if(i == 2) {
        			platforms.get(i).bigDraw(g, Color.DARK_GRAY);
        			gandalf.bigCollisonPlatform(platforms.get(i));
        		}
        		else{
        			platforms.get(i).draw(g, Color.DARK_GRAY);
        		}
	        }
        	losing(g);
        }
        else if(level3 == true) {
        	g.drawImage(background3, 0, 0, 1280, 900, this);
        	g.drawImage(gold, 1030, 650, 200, 175, this);
        	g.drawImage(gold, 1080, 700, 200, 175, this);
        	g.drawImage(gold, 980, 700, 200, 175, this);
        	g.drawImage(gold, 1030, 700, 200, 175, this);
        	for (int i = 0; i < platforms.size(); i++) {
	            platforms.get(i).draw(g, Color.GRAY);
	        }
        	createFire(g);
        	gandalf.draw(g, this);
        	g.drawImage(goal, goalX, goalY, goalW, goalH, this);
        	dragon.drawD(g, this);
        	losing(g);
        	win(g);
        }
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
        gandalf.move();
        gandalf.gravity();
        screenSides();                        			//method to keep gandalf inside the screen
//        checkPlatforms();                    			//check the location of the moving platform
//        movePlat();                           		//move platform
        gandalf.collisionPlatfrom(platforms);
        if(level1 == true) {
        	if(level0 ==false) {
            checktroll1();
            checktroll2();
            troll1.moveFast();  
            troll2.moveFast();
	        gandalf.collisionEnemy(troll1);
	        gandalf.collisionEnemy(troll2);
        	}
        }
        else if(level2 == true) {
        	checkGoblin1();
        	goblin1.moveSlow();
        	checkGoblin3();
        	checkGoblin4();
        	goblin3.moveSlow();
        	goblin4.moveSlow();
        	checkPlatforms();
        	platforms.get(1).move();
        	colwS();
        	gandalf.collisionGoblin(goblin1);
           	gandalf.collisionGoblin(goblin2);
           	gandalf.collisionGoblin(goblin3);
           	gandalf.collisionGoblin(goblin4);
        }
        else if(level3 == true) {
        	checkDragon();
        	dragon.moveDragon();
        	if(fireballs.size() > 0) {
        		gandalf.collisionFireball(fireballs);
        		if(fireballs.get(0).getX() >= 1290 || fireballs.get(0).getX() <= -200) {
        			fireballs.clear();
        		}
        	}
//        	checkPlatform();
//        	platforms.get(2).moveLR();
        	gandalf.collisionDragon(dragon);
        }
        
    }
    
    public void win(Graphics g) {
    	// x1<=x2+W2 && x2<=x1+W1 && y1<=y2+H2 && y2<=y1+H1
    	if(gandalf.getX() <= goalX + goalW && goalX <= gandalf.getX() + gandalf.getWidth() && gandalf.getY() <= goalY + goalH && goalY <= gandalf.getY() + gandalf.getHeight()) {
    		level++;
    		levelChanger(level);
    		g.drawImage(theEnd, 0, 0, 1280, 900, this);
    		System.out.println("Win is true");
    	}
    }
    
    public void losing(Graphics g) {
    	if(gandalf.getDeaths() > 0) {
    		g.setColor(Color.DARK_GRAY);
    		g.fillRect(0, 0, 1280, 900);
    		g.drawImage(gameOver, 500, 300, 250, 200, this);
    	}
    }
    public void colwS() {
    	// x1<=x2+W2 && x2<=x1+W1 && y1<=y2+H2 && y2<=y1+H1
    	if(gandalf.getX() <= (polygonX[0]+15) + 30 && (polygonX[0]+15) <= gandalf.getX() + gandalf.getWidth() && gandalf.getY() <= polygonY[0] + 250 && polygonY[0] <= gandalf.getY() + gandalf.getHeight()) {
    		gandalf.setX((polygonX[0]+15) - gandalf.getWidth());
    	}
    }
    
    public void createFire(Graphics g) {
    	if(dragon.isMoveLeft()==true) {
	    	if(dragon.getY() + 85 >= gandalf.getY() && dragon.getY() - 85 <= gandalf.getY() && dragon.getX() > gandalf.getX()) {
	    	    fireballs.add(new Fireball(dragon.getX(), dragon.getY()+50, 75, 75, frameFire()));
	    	    if(frameFire() == 7) {
	    	    	fireballs.get(0).setMoveLeft(true);
	    	    }
	    	    else {
	    	    	fireballs.get(0).setMoveRight(true);
	    	    }
	    		System.out.println("FIRE");
	    	}
	    	if(fireballs.size() > 0) {
	    		fireballs.get(0).draw(g, this);
	    		fireballs.get(0).move();
	    	}
    	}
    	else {
    		if(dragon.getY() + 35 >= gandalf.getY() && dragon.getY() - 35 <= gandalf.getY() && dragon.getX() < gandalf.getX()) {
	    	    fireballs.add(new Fireball(dragon.getX()+230, dragon.getY()+20, 75, 75, frameFire()));
	    	    if(frameFire() == 7) {
	    	    	fireballs.get(0).setMoveLeft(true);
	    	    }
	    	    else {
	    	    	fireballs.get(0).setMoveRight(true);
	    	    }
	    		System.out.println("FIRE");
	    	}
	    	if(fireballs.size() > 0) {
	    		fireballs.get(0).draw(g, this);
	    		fireballs.get(0).move();
	    	}
    	}
    }
    
    public int frameFire() {
    	if(dragon.isMoveLeft() == true) {
			return 7;
		}
		else{
			return 0;
		}
    }
        
    public void screenSides() {
        if (gandalf.getX() == 1280) {
        	level++;
            levelChanger(level);
            gandalf.setX(0);
        }
        if (gandalf.getX() < 0) {
            gandalf.setX(0);
        }
        if (gandalf.getY() > 900) {
            gandalf.setY(900);
        }
        if (gandalf.getY() < 0) {
            gandalf.setY(0);
        }
    }
    
    public void levelChanger(int level) {
    	if(level == 0) {
    		level1 = true;
    		level2 = false;
    		level3 = false;
    		
    	}
    	else if(level == 1) {
    		level1 = false;
    		level2 = true;
    		level3 = false;
    		platformX.set(0, 60);
        	platformX.set(1, 270);
        	platformX.set(2, 510);
        	platformX.set(3, 975);
        	platformX.set(4, 1125);
        	
        	platformY.set(0, 350);
        	platformY.set(1, 540);
        	platformY.set(2, 300);
        	platformY.set(3, 550);
        	platformY.set(4, -800);
        	 for (int i = 0; i < platformX.size(); i++) {
             	platforms.set(i,(new Platforms(platformX.get(i), platformY.get(i), 125, 20)));
             }
    	}
    	else if(level == 2) {
    		level1 = false;
    		level2 = false;
    		level3 = true;
    		platformX.set(0, 60);
        	platformX.set(1, 270);
        	platformX.set(2, 510);
        	platformX.set(3, 900);
        	platformX.set(4, 1125);
        	
        	platformY.set(0, 650);
        	platformY.set(1, 500);
        	platformY.set(2, 300);
        	platformY.set(3, 300);
        	platformY.set(4, 200);
        	 for (int i = 0; i < platformX.size(); i++) {
             	platforms.set(i,(new Platforms(platformX.get(i), platformY.get(i), 125, 20)));
             }
    	}
    	else if(level == 3){
    		System.out.println("The End...");
    	}
    }
    public void dragMouse(MouseEvent e) {
    }
    public void releaseMouse(MouseEvent e, Graphics g) {
    }

    // DON't TOUCH THE INITIALIZING COMPONENTS METHOD
    private void initComponents() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                dragMouse(e);
                repaint();
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Graphics g = null;
                releaseMouse(e, g);
            }
        });
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
    }

    public void checkPlatforms() {
        if (platforms.get(1).getY() >= 700) {
        	platforms.get(1).setDown(false);
        	platforms.get(1).setUp(true);
        }
        if (platforms.get(1).getY() <= 540) {
        	platforms.get(1).setUp(false);
        	platforms.get(1).setDown(true);
        }
    }  
    public void checktroll1() {
        if (troll1.getX() <= 0) {
        	troll1.setMoveLeft(false);
        	troll1.setMoveRight(true);
            troll1.setFrame(0);
        }
        if (troll1.getX() >= 635 - troll1.getWidth()) {
        	troll1.setMoveRight(false);
        	troll1.setMoveLeft(true);
        	troll1.setFrame(8);
        }
    }
    
    public void checkDragon() {
        if (dragon.getX() <= -550) {
        	n = r.nextInt(3);
        	dragon.setY(dragonYs[n]);
        	dragon.setMoveLeft(false);
        	dragon.setMoveRight(true);
        	dragon.setFrame(0);
            System.out.println("Moving Right");
        }
        if (dragon.getX() >= 1580) {
        	n = r.nextInt(3);
        	dragon.setY(dragonYs[n]);
        	dragon.setMoveRight(false);
        	dragon.setMoveLeft(true);
        	dragon.setFrame(8);
        	System.out.println("Moving Left");
        }
    }
    
    public void checktroll2() {
        if (troll2.getX() <= 645) {
        	troll2.setMoveLeft(false);
        	troll2.setMoveRight(true);
        	troll2.setFrame(0);
        }
        if (troll2.getX() >= 1280 - troll2.getWidth()) {
        	troll2.setMoveRight(false);
        	troll2.setMoveLeft(true);
        	troll2.setFrame(8);
        }
    }
    
    public void checkGoblin3() {
        if (goblin3.getX() <= 0) {
        	goblin3.setMoveLeft(false);
        	goblin3.setMoveRight(true);
        	goblin3.setFrame(0);
        }
        if (goblin3.getX() >= 640 - goblin3.getWidth()) {
        	goblin3.setMoveRight(false);
        	goblin3.setMoveLeft(true);
        	goblin3.setFrame(8);
        }
    }
    
    public void checkGoblin4() {
        if (goblin4.getX() <= 640) {
        	goblin4.setMoveLeft(false);
        	goblin4.setMoveRight(true);
        	goblin4.setFrame(0);
        }
        if (goblin4.getX() >= 1280 - goblin4.getWidth()) {
        	goblin4.setMoveRight(false);
        	goblin4.setMoveLeft(true);
        	goblin4.setFrame(8);
        }
    }
    
    public void checkGoblin1() {
        if (goblin1.getX() <= 510) {
        	goblin1.setMoveLeft(false);
        	goblin1.setMoveRight(true);
        	goblin1.setFrame(1);
        }
        if (goblin1.getX() >= 885 - goblin1.getWidth()) {
        	goblin1.setMoveRight(false);
        	goblin1.setMoveLeft(true);
        	goblin1.setFrame(8);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        	level0=false;
            System.out.println("Pressed ENTER");
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && gandalf.getJumpLimit() < 2) {
            gandalf.setJumpLimit(gandalf.getJumpLimit() + 1);
            gandalf.setGravity(-18);
            System.out.println("Pressed SPACE");
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gandalf.setMoveLeft(true);
            System.out.println("Pressed A");
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gandalf.setMoveRight(true);
            System.out.println("Pressed D");
        }
    }
    public void keyReleased(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            gandalf.setMoveLeft(false);
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            gandalf.setMoveRight(false);
        }
    }
}