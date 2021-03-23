import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class Login extends JFrame{
	
	public static ResultSet rs;
	public static String USERNAME="", PASSWORD="";
	   static Connection con;
	   public static Statement st;
	    
	    
	    private static final long serialVersionUID = 1L;
	    private JTextField usernameField;
	    private JPasswordField passwordField;
	    private JButton btnLogin;
	    private JLabel label;
	    private JPanel contentPane;
	    
	    

	
//Launch app
	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Login frame = new Login();
	                    frame.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }


	
	   //Create Frame
	    public Login() {
	    	
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(700, 190, 500, 400);
	        setResizable(false);
	        contentPane = new JPanel();
	        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	        setContentPane(contentPane);
	        contentPane.setLayout(null);

	        JLabel lblNewLabel = new JLabel("Login");
	        lblNewLabel.setBounds(50, 50, 50, 50);
	        contentPane.add(lblNewLabel);

	        usernameField = new JTextField();
	        usernameField.setFont(new Font("Test", Font.PLAIN, 16));
	        usernameField.setBounds(200, 100, 200, 30);
	        contentPane.add(usernameField);
	        usernameField.setColumns(10);

	        passwordField = new JPasswordField();
	        passwordField.setFont(new Font("Test", Font.PLAIN, 16));
	        passwordField.setBounds(200, 150, 200, 30);
	        contentPane.add(passwordField);

	        JLabel lblUsername = new JLabel("Username");
	        lblUsername.setBounds(50 ,100, 100, 30);
	        contentPane.add(lblUsername);

	        JLabel lblPassword = new JLabel("Password");
	        lblPassword.setBounds(50 ,150, 100, 30);
	        contentPane.add(lblPassword);

	        btnLogin = new JButton("Login");
	        btnLogin.setBounds(200 ,250, 100, 40);
	        btnLogin.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                String USERNAME = usernameField.getText();
	                String PASSWORD = passwordField.getText();
	                try {
	                    Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2", "root", "password");
	                    PreparedStatement st = (PreparedStatement) connection
	                        .prepareStatement("Select USERNAME, PASSWORD from students where USERNAME=? and PASSWORD=?");
	                   

	                    st.setString(1, USERNAME);
	                    st.setString(2, PASSWORD);
	                    ResultSet rs = st.executeQuery();
	               
	                    
	                    if (rs.next()) {
	                    	Connection connection2 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2", "root", "password");
	                    	PreparedStatement st2 = (PreparedStatement) connection2
			                        .prepareStatement("UPDATE students SET TOT_LOGINS = TOT_LOGINS + 1 WHERE USERNAME= '" + usernameField.getText() + "'");
	                    	st2.executeUpdate();
		                    
	                    	
	 	                    
	                    	JOptionPane.showMessageDialog(btnLogin, "Sucessful Login!");
	                        dispose();
	                        Client myClient = new Client();
	                        myClient.setVisible(true);
	                        
	                        
	                    } else {
	                        JOptionPane.showMessageDialog(btnLogin, "Incorrect Login Details!");
	                    }
	                } catch (SQLException sqlException) {
	                    sqlException.printStackTrace();
	                }
	            }
	        });

	        contentPane.add(btnLogin);

	        label = new JLabel("");
	        label.setBounds(0, 0, 1008, 562);
	        contentPane.add(label);
	    }
	}