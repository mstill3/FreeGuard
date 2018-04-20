/*
 * 412: Software Engineering
 * Team Honey Badgers
 * 4-20-2017
 * 
 * This is the main runner class
 */

package freeguard;
import javax.swing.JFrame;

public class Runner
{

    public static void main(String[] args)
    {
        NewUserFrame u = new NewUserFrame();
        JFrame myFrame = new JFrame("Test");
        myFrame.add(u);
        myFrame.pack();
        myFrame.setSize(400, 400);
        myFrame.setVisible(true);
        u.init();
    }
    
}
