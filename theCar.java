/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hillclimbracing1;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author echav
 */
public class theCar extends JPanel implements ActionListener,KeyListener{
private int space;
private int speed;
private int width=80;
private int height=70;
private int WIDTH=500;
private  int count=1;
private int move=30;
private int HEIGHT=700;
private Random rand;
private ArrayList <Rectangle> ocars;
private ArrayList <Rectangle> line;
private Rectangle car;
BufferedImage road;
BufferedImage op1;
BufferedImage user;
BufferedImage bg;
Boolean linef=true; 
Timer  t;
public theCar() throws IOException{
    try {
        op1=ImageIO.read(new File("C:\\Users\\echav\\Documents\\images\\op1.png"));   
        bg=ImageIO.read(new File("C:\\Users\\echav\\Documents\\images\\bg.png"));
        road=ImageIO.read(new File("C:\\Users\\echav\\Documents\\images\\road.png"));
        user=ImageIO.read(new File("C:\\Users\\echav\\Documents\\images\\user.png"));
    } catch (IOException ex) {
        Logger.getLogger(theCar.class.getName()).log(Level.SEVERE, null, ex);
    }
    t= new Timer(25,this);
    rand=new Random();
    ocars=new ArrayList<Rectangle>();
    line=new ArrayList<Rectangle>();
    car= new Rectangle(WIDTH/2-90,HEIGHT-100,width,height);
    space=300;
    speed=2;
    addKeyListener(this);
    setFocusable(true);
    t.start();
    addocars(true);
    addocars(true);  
    addocars(true);
    addlines(true);
        addlines(true);
            addlines(true);
                addlines(true);
    
}
public void addlines(Boolean First){
    int x=WIDTH/2-2;
    int y=0;
    int width=4;
    int height=50;
    int space=20;
    if(linef){
        line.add(new Rectangle(x,y-(line.size()*20),width,height));
    }else{
        line.add(new Rectangle(x,line.get(line.size()-1).y-space,width,height));
    }
}
public void addocars(boolean first){
    int positionx=rand.nextInt()%2;
    int x = 0;
    int y = 0;
    int Width=width; 
    int Height=height;
    if (positionx==0){
         x=WIDTH/2-90;
         
    }
    else{
        x=WIDTH/2+10;
    }
    if (first){
        ocars.add(new Rectangle(x,y-100-(ocars.size()*space),Width,Height));
    }
    else{
       ocars.add(new Rectangle(x,ocars.get(ocars.size()-1).y-400,Width,Height)); 
    }
}
public void paintComponent (Graphics g){
    super.paintComponents(g);
    g.drawImage(bg, 0, 0, null);
    g.drawImage(road, WIDTH/2-99, 0, null);
    for (Rectangle rect:line){
        g.setColor(Color.white);
       g.fillRect(rect.x, rect.y, rect.width, rect.height);
 
    }
     g.drawImage(user, car.x, car.y, null);
   g.drawLine( WIDTH/2,0,WIDTH/2, HEIGHT);
   g.setColor(Color.blue);
  for (Rectangle rect:ocars){
       if(rand.nextInt()%2==0){
       g.drawImage(op1, rect.x, rect.y, null);

       }
   
   }
}

    
    public void actionPerformed(ActionEvent e) {
        Rectangle rect;
        count++;
        for(int i=0;i<ocars.size();i++){
        rect=ocars.get(i);
        if (count%1000==0){
            speed++;
            if(move<60){
                move+=10;
            }
        }
        rect.y+=speed;
    }
        for (Rectangle r:ocars){
            if(r.intersects(car)){
                car.y=r.y+height;
            }
        }
        for(int i=0;i<ocars.size();i++){
        rect=ocars.get(i);
        if(rect.y+rect.height>HEIGHT){
            ocars.remove(rect);
            addocars(false);
        }           
        
    }
         for(int i=0;i<line.size();i++){
        rect=line.get(i);
        if (count%1000==0){
            speed++;
           
        }
        rect.y+=speed;
         }
        for(int i=0;i<line.size();i++){
        rect=line.get(i);
        if(rect.y+rect.height>HEIGHT){
            line.remove(rect);
            addlines(false);
        }
        }
        
        repaint();
    }
public void moveUp(){
    if(car.y-move<0){
System.out.println("/b");
    }else{
        car.y-=move;
    }
}
    public void moveDown(){
    if(car.y+move+car.height>HEIGHT-1){
System.out.println("\b");
    }else{
        car.y+=move;
    }
}
    public void moveLeft(){
    if(car.x-move<WIDTH/2-90){
System.out.println("\b");
    }else{
        car.x-=move;
    }
}
    public void moveRight(){
    if(car.x+move>WIDTH/2+10){
System.out.println("\b");
    }else{
        car.x+=move;
    }
}
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
     
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        switch (key){
            case KeyEvent.VK_UP -> moveUp();
            case KeyEvent.VK_DOWN -> moveDown();
            case KeyEvent.VK_LEFT -> moveLeft();
            case KeyEvent.VK_RIGHT -> moveRight();
            default -> {
        }
        }
        
    }

   
    
}
