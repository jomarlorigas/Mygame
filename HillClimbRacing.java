/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hillclimbracing;

/**
 *
 * @author echav
 */
   import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HillClimbRacing extends JPanel implements KeyListener {
    private JFrame frame;
    private Image background;
    private Image car;
    private int carX, carY;

    public HillClimbRacing() {
        frame = new JFrame("2D Racing Game");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(this);
        
        background = Toolkit.getDefaultToolkit().getImage("src/images/hill.png");
        car = Toolkit.getDefaultToolkit().getImage("src/images/car.png");
        carX = 2;
        carY = 100;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 350, 450, null);
        g.drawImage(car, carX, carY, null);
    }

    public void moveCar(int keyCode) {
        int speed = 10;
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                carX -= speed;
                break;
            case KeyEvent.VK_RIGHT:
                carX += speed;
                break;
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        moveCar(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void startGame() {
        frame.getContentPane().add(this);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
       HillClimbRacing game = new HillClimbRacing();
        game.startGame();
    }
}
