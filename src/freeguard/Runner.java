/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package freeguard;

import javax.swing.JFrame;

/**
 *
 * @author Keenan
 */
public class Runner {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        NewUserFrame u = new NewUserFrame();
        JFrame myFrame = new JFrame("Test");
        myFrame.add(u);
        myFrame.pack();
        myFrame.setVisible(true);
        u.init();
    }
    
}
