import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;


public class Login {
	
	public static ResultSet rs;
	public static String USERNAME="", PASSWORD="";
	   static Connection con;
	    static Statement st;
///////////////
	
 public static void main(String args[]) {
 JFrame f = new JFrame("Conor Brett - Assignment 1");
 	
	final JLabel usernameLabel = new JLabel("Username: ");
	JLabel passwordLabel = new JLabel("Password: ");
	final JTextField usernameField = new JTextField(65);
	final JTextField passwordField = new JTextField(65);
	JButton next = new JButton("Next");
	JButton prev = new JButton("Prev");

	
	
	

	
	try{
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/assign2", "root", "password");
		Statement st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rs=st.executeQuery("select * from students");
		
		
	}
		
	catch(Exception e){}
	
		JPanel p = new JPanel(new GridLayout(8,8));
		p.add(usernameLabel);
		p.add(usernameField);
		p.add(passwordLabel);
		p.add(passwordField);
	
		p.add(next);
		p.add(prev);

		f.add(p);
		f.setVisible(true);
		f.pack();
		
		
	  
		//Handles Previous Button
		prev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rs.previous()){
						USERNAME=rs.getString("USERNAME");
						usernameField.setText(USERNAME);
						
						PASSWORD=rs.getString("PASSWORD");
						passwordField.setText(PASSWORD);
				
				
				
					}
				} catch (SQLException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		//Handles Next Button
		next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (rs.next()){
						USERNAME=rs.getString("USERNAME");
						usernameField.setText(USERNAME);
						
						PASSWORD=rs.getString("PASSWORD");
						passwordField.setText(PASSWORD);
						
				
				
					}
				} catch (SQLException e) {
					//TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		
	}





}