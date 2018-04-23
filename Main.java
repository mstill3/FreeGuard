/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg412project;

/**
 *
 * @author Keenan
 */
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        newUserFrame u=new newUserFrame();
        JFrame myFrame=new JFrame("Test");
        myFrame.add(u);
        myFrame.pack();
        myFrame.setVisible(true);
        u.init();
    }
    
}
