

package com.mycompany.hillclimbracing1;
import java.io.IOException;
import javax.swing.JFrame;

public class Hillclimbracing1 extends JFrame {

    public static void main(String[] args) throws IOException {
       JFrame jf = new JFrame();
       loadingScreen n=new  loadingScreen();
       theCar h = new theCar();
       jf.add(h);
      jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      jf.setSize(500,720);
      jf.setVisible(true);
       
    }

   
    }

