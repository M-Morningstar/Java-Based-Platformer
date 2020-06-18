import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    GUI b;

    public KeyInput(GUI g) {
        b=g;
    }
    public void keyPressed(KeyEvent e) {
        b.keyPressed(e);
    }
    public void keyReleased(KeyEvent e) {
        b.keyReleased(e);
    }
}







