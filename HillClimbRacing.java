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

public class HillClimbRacing extends JFrame implements KeyListener, Runnable {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private Image backgroundImage;
    private Image carImage;
    private int carX = WIDTH / 2;
    private int carY = HEIGHT - 100;
    private int carSpeed = 0;
    private int carAngle = 0;

    public HillClimbRacing() {
        setTitle("2D Car Racing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setResizable(false);
        addKeyListener(this);

        backgroundImage = new ImageIcon("path_to_background_image.jpg").getImage(); // Replace with the actual background image file path
        carImage = new ImageIcon("path_to_car_image.png").getImage(); // Replace with the actual car image file path

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(backgroundImage, 0, 0, WIDTH, HEIGHT, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.rotate(Math.toRadians(carAngle), carX + carImage.getWidth(null) / 2, carY + carImage.getHeight(null) / 2);
        g2d.drawImage(carImage, carX, carY, null);
    }

    private void moveCar() {
        double radians = Math.toRadians(carAngle);
        int dx = (int) Math.round(carSpeed * Math.cos(radians));
        int dy = (int) Math.round(carSpeed * Math.sin(radians));

        if (carX + dx < 0 || carX + dx > WIDTH - carImage.getWidth(null) ||
                carY + dy < 0 || carY + dy > HEIGHT - carImage.getHeight(null)) {
            return;
        }

        carX += dx;
        carY += dy;
        repaint();
    }

    @Override
    public void run() {
        while (true) {
            moveCar();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                carSpeed = Math.min(carSpeed + 1, 10);
                break;
            case KeyEvent.VK_DOWN:
                carSpeed = Math.max(carSpeed - 1, -5);
                break;
            case KeyEvent.VK_LEFT:
                carAngle = (carAngle - 5) % 360;
                break;
            case KeyEvent.VK_RIGHT:
                carAngle = (carAngle + 5) % 360;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        HillClimbRacing game = new HillClimbRacing();
        new Thread(game).start();
    }
}
