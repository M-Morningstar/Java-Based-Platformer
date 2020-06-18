//Mert Salih Osoydan 
//2020/06/07 - 2020/06/15
//The goal is to reach the Arkenstone in one piece.

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
         JFrame w = new JFrame();
         w.setSize(1280,900);
         w.setTitle("Gandalf, The Fake Hobbit");
         w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         GUI m = new GUI();
         w.add(m);
         w.addKeyListener(new KeyInput(m));
         w.setVisible(true);   
    }

}












