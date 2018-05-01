package freeguard;

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
public class ViewClaimForm extends JFrame
{

    private JFrame resultFrame; //declare variable
    private JTable resultTable;
    DatabaseManager db = new DatabaseManager();
    // database URL
    String DB_URL = db.JDBC_DB_URL;

    //  Database credentials
    String USER = db.username;
    String PASS = db.password;

    static final int COLUMN_NUM = 2;

    public ViewClaimForm(String username)
    {

        resultFrame = new JFrame();      //create object of JPanel
        resultFrame.setTitle("View Claims");                                                                                                //set title
        resultFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);                        //set close operation                                                                          
        resultFrame.setSize(450, 300);                                      //set contentPane border

        resultFrame.setAlwaysOnTop(true);

        //string builder used to format and store the resulting tables
        int rowNum = 0;

        JLabel lblTitle = new JLabel("Claims");
        lblTitle.setBounds(70, 25, 86, 14);

        String SQuery = "select db412.SSClaims.idSSClaims,"
                + "db412.SSClaims.Moneys \n"
                + "FROM db412.SSClaims, db412.accounts\n"
                + "WHERE '" + username + "' = accounts.email AND SSClaims.CustomerSSN = accounts.SSN;";
        System.out.println("Connecting to a selected database...");
        System.out.println(SQuery);

        Wrapper snoopdog = null;                                                                //Create wrapper object and define it null
        try                                                                                                                                                          //try block
        {

            snoopdog = DriverManager.getConnection(DB_URL, USER, PASS);                            // Open a connection
            System.out.println("Connected database successfully...");
            Statement stmt = ((Connection) snoopdog).createStatement();
            ResultSet rs = stmt.executeQuery(SQuery);
            ResultSetMetaData rsmd = rs.getMetaData();

            Statement stmt2 = ((Connection) snoopdog).createStatement();
            ResultSet goofy = stmt2.executeQuery(SQuery);
            int size = 0;
            goofy.next();
            while (goofy.next())
                size++;

            rowNum = size;                                                          //Get number of Columns
            System.out.println("DFDSF " + rowNum);
            Object[][] cellData = new Object[rowNum][COLUMN_NUM];
            String[] columnNames = new String[COLUMN_NUM];

            for (int i = 0; i < COLUMN_NUM; i++)
                columnNames[i] = rsmd.getColumnName(i + 1);

            int i = 0;
            rs.next();
            while (rs.next())
            {
                for (int j = 0; j < COLUMN_NUM; j++)   //for loop nested in while loop to retrieve the results table
                {

                    System.out.println(rs.getString(j + 1));
                    cellData[i][j] = rs.getString(j + 1);
                }
                i++;
            }
            // JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);
            //close connection
            ((java.sql.Connection) snoopdog).close();

            for (Object o : cellData)
                System.out.println(o);

            for (String s : columnNames)
                System.out.println(s);

            resultTable = new JTable(cellData, columnNames);
            resultFrame.add(new JScrollPane(resultTable));
            resultFrame.setVisible(true);
        } catch (SQLException se)
        {
            //handle errors for JDBC
            se.printStackTrace();
        } catch (Exception a) //catch block
        {
            a.printStackTrace();
        }

    }

}
