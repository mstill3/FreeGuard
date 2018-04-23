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
//import statement
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class newuser extends JFrame //create class NewUser
{

    private JPanel contentPane; //declare variable
    private JTextField txtUser;
    private JButton btnSignup;
    private JTextField txtPassword;
    protected java.lang.String Spassword;

    // database URL
    static final String DB_URL = "jdbc:mysql://db412.cklzsxoqzmcc.us-east-2.rds.amazonaws.com";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root";
    protected static final String String = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) // main method
    {
        EventQueue.invokeLater(new Runnable() {
            public void run() //define run method
            {
                try //try block
                {
                    //create NewUser frame object
                    newuser frame = new newuser();
                    //set NewUser frame visible
                    frame.setVisible(true);
                } catch (Exception e) //catch block
                {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public newuser() //create constructor
    {
        //set title
        setTitle("New User Login");
        //set close operation
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //set bounds of frame
        setBounds(100, 100, 450, 300);
        //create object of JPanel
        contentPane = new JPanel();
        //set contentPane border
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        //set ContentPane with new object;
        setContentPane(contentPane);
        //set contentPane layout is null
        contentPane.setLayout(null);

        // create text field for user
        txtUser = new JTextField();
        //set bounds for text fields
        txtUser.setBounds(188, 51, 99, 20);
        //in contentPane add text field
        contentPane.add(txtUser);
        //set column for text field
        txtUser.setColumns(10);

        //lable the text field
        JLabel lblUserName = new JLabel("User Name");
        //set bounds for label
        lblUserName.setBounds(70, 54, 86, 14);
        //add into contentPane
        contentPane.add(lblUserName);

        //lable the text field
        JLabel lblPassword = new JLabel("Password");
        //set bounds for label
        lblPassword.setBounds(70, 109, 86, 14);
        //add into contentPane
        contentPane.add(lblPassword);

        //create button signup
        btnSignup = new JButton("SignUp");
        //add event handler on SignUp button
        btnSignup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Create wrapper object and define it null
                Wrapper conn = null;
                try {
                                    //Class.forName("com.mysql.jdbc.Driver");  

                    //declare variables
                    String username = "";
                    String password = "";

                    //get values using getText() method
                    username = txtUser.getText().trim();
                    password = txtPassword.getText().trim();

                    //variables for password strength verification
                    String lowchars = "abcdefghijklmnopqrstuvwxyz";
                    String upchars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    String nums = "1234567890";
                    String syms = "!@#$%^&*-=+_,.`~";
                    int lowcharcount = 0;
                    int upcharcount = 0;
                    int numcount = 0;
                    int symcount = 0;
                    boolean valid = true;
                    //get password character information
                    for (int i = 0; i < password.length(); i++) {
                        if (lowchars.contains(password.substring(i, i + 1)))
                        lowcharcount++;
                        if (upchars.contains(password.substring(i, i + 1)))
                        upcharcount++;
                        if (nums.contains(password.substring(i, i + 1)))
                        numcount++;
                        if (syms.contains(password.substring(i, i + 1)))
                        symcount++;
                    }

                    // check condition it field equals to blank throw error message
                    if (username.equals("") || password.equals("")) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " name or password is empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //check password length
                    if (password.length() < 8 && valid == true) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " password too short", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //check for at least 1 lower case character
                    if (lowcharcount == 0 && valid == true) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " password must contain at least 1 lowercase character", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //check for at least 1 upper case character
                    if (upcharcount == 0 && valid == true) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " password must contain at least 1 uppercase character", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //check for at least number character
                    if (numcount == 0 && valid == true) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " password must contain at least 1 number", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    //check for at least symbol character
                    if (symcount == 0 && valid == true) {
                        valid = false;
                        JOptionPane.showMessageDialog(null, " password must complain at least 1 symbol", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if(valid)//else insert query is run properly
                    {
                        String IQuery = "INSERT INTO `412project`.`accounts`(`username`,`password`) VALUES('" + username + "', '" + password + "')";
                        System.out.println(IQuery);//print on console
                        System.out.println("Connecting to a selected database...");

                        //STEP 3: Open a connection
                        conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        System.out.println("Connected database successfully...");

                        ((Connection) conn).createStatement().execute(IQuery);//select the rows
                        // define SMessage variable
                        String SMessage = "Record added for " + username;

                        // create dialog ox which is print message
                        JOptionPane.showMessageDialog(null, SMessage, "Message", JOptionPane.PLAIN_MESSAGE);
                        //close connection
                        ((java.sql.Connection) conn).close();
                    }
                } catch (SQLException se) {
                    //handle errors for JDBC
                    se.printStackTrace();
                } catch (Exception a) //catch block
                {
                    a.printStackTrace();
                }
            }
        });
        //set bound for SignUp button
        btnSignup.setBounds(131, 165, 89, 23);
        //add button into contentPane
        contentPane.add(btnSignup);

        //create text field for password
        txtPassword = new JTextField();
        //set bound for password field
        txtPassword.setBounds(188, 106, 99, 20);
        //add text field on contentPane
        contentPane.add(txtPassword);
        //set column for password text field
        txtPassword.setColumns(10);
    }

}
