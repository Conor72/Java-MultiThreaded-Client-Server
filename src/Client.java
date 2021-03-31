import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

// Text field for receiving radius
  public static JTextField jtf = new JTextField();

  // Text area to display contents
  public static JTextArea jta = new JTextArea();
  
  public static String USERNAME="";
  public static String TOT_LOGINS = "";
  

  // IO streams
  private DataOutputStream toServer;
  private DataInputStream fromServer;

  public static void main(String[] args) {
    new Client();
  }

  public Client() {
    // Panel p to hold the label and text field
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    jta.append("Welcome " + USERNAME + '\n' + "You have logged in a total of " + TOT_LOGINS + " times" + '\n');
    p.add(new JLabel("Enter radius"), BorderLayout.WEST);
    p.add(jtf, BorderLayout.CENTER);
    jtf.setHorizontalAlignment(JTextField.RIGHT);

    setLayout(new BorderLayout());
    add(p, BorderLayout.NORTH);
    add(new JScrollPane(jta), BorderLayout.CENTER);

    jtf.addActionListener(new Listener()); // Register listener

    setTitle("Client");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
     
      

      Scanner scn = new Scanner(System.in); 
		
		// getting localhost ip 
		InetAddress ip = InetAddress.getByName("localhost"); 

		// establish the connection with server port 5056 
		 Socket serverSocket = new Socket(ip, 5056);

		
      // Create an input stream to receive data from the server
      fromServer = new DataInputStream(serverSocket.getInputStream());

      // Create an output stream to send data to the server
      toServer = new DataOutputStream(serverSocket.getOutputStream());
      
      
      toServer.writeUTF(USERNAME);
    }
    catch (IOException ex) {
      jta.append(ex.toString() + '\n');
    }
  }

  private class Listener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	
      try {
        // Get the radius from the text field
        double radius = Double.parseDouble(jtf.getText().trim());

        // Send the radius to the server
        toServer.writeDouble(radius);
        toServer.flush();

        // Get area from the server
        double area = fromServer.readDouble();

        // Display to the text area
        jta.append("Radius is " + radius + "\n");
        jta.append("Area received from the server is "
          + area + '\n');
      }
      catch (IOException ex) {
        System.err.println(ex);
      }
    }
  }
}