package freeguard;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * @author Kyle Clabough COSC 412
 *
 */
public class ViewClaim extends JFrame
{

    private JFrame resultFrame; //declare variable
    private JTable resultTable;

    // database URL
    static final String DB_URL = "jdbc:mysql://freeguardcosc412.cydcykknz8wc.us-east-1.rds.amazonaws.com:3306/mysql?zeroDateTimeBehavior=convertToNull";

    //  Database credentials
    static final String USER = "pi";
    static final String PASS = "gooseberry";
    protected static final String String = null;
    static final int rowNum = 2;

    public ViewClaim(String username)
    {
        
        resultFrame = new JFrame();      //create object of JPanel
        resultFrame.setTitle("View Claims");                                                                                                //set title
        resultFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                        //set close operation                                                                          
        resultFrame.setSize(450, 300);                                      //set contentPane border



        //string builder used to format and store the resulting tables
        int columnNum = 0;
        
        JLabel lblTitle = new JLabel("Claims");
        lblTitle.setBounds(70, 25, 86, 14);

        String SQuery = "select freeguardcosc412db.CLAIMS.CLAIMNO,"
                + "freeguardcosc412db.CLAIMS.AMOUNT \n"
                + "FROM freeguardcosc412db.CLAIMS, freeguardcosc412db.Accounts\n"
                + "WHERE '" + username + "' = Accounts.USERNAME AND CLAIMS.CSSN = Accounts.SSN;";
        System.out.println("Connecting to a selected database...");
        System.out.println(SQuery);

        Wrapper conn = null;                                                                //Create wrapper object and define it null
        try                                                                                                                                                          //try block
        {

            conn = DriverManager.getConnection(DB_URL, USER, PASS);                            // Open a connection
            System.out.println("Connected database successfully...");
            Statement stmt = ((Connection) conn).createStatement();
            ResultSet rs = stmt.executeQuery(SQuery);
            ResultSetMetaData rsmd = rs.getMetaData();
            columnNum = rsmd.getColumnCount();                                                          //Get number of Columns
            Object [] [] cellData = new Object [columnNum] [rowNum];
            String [] columnNames = new String [columnNum];
            for (int i = 0; i< rowNum; i++)
            {
                 columnNames [i] = rsmd.getColumnName(i+1);
            }
            int i = 0;
            while (rs.next())
            {
                for (int j = 0; j < columnNum; j++)   //for loop nested in while loop to retrieve the results table
                {
                    cellData [i] [j] = rs.getString(j+1);
                }
                i++;
            }
            // JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);
            //close connection
            ((java.sql.Connection) conn).close();
            resultTable =  new JTable (cellData, columnNames);
            resultFrame.add(new JScrollPane(resultTable));
            resultFrame.setVisible(true);
        }
        catch (SQLException se)
        {
            //handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception a) //catch block
        {
            a.printStackTrace();
        }

    }

 /*   public static void main(String[] args)
    {
        try  //try block
        {
            //create NewUser frame object
            Login frame = new Login();
            //set NewUser frame visible
            frame.setVisible(true);
        }
        catch (Exception e) //catch block
        {
            e.printStackTrace();
        }
    }*/
}
