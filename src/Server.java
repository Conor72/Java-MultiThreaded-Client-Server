import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame {
  /**
	 * 
	 */

	
	
	private static final long serialVersionUID = 1L;
// Text area for displaying contents
  private JTextArea jta = new JTextArea();

  public static void main(String[] args) {
    new Server();
  }

  public Server() {
    // Place text area on the frame
    setLayout(new BorderLayout());
    add(new JScrollPane(jta), BorderLayout.CENTER);

    setTitle("Server");
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true); // It is necessary to show the frame here!

    try {
      // Create a server socket
      ServerSocket serverSocket = new ServerSocket(5056);
      jta.append("Server started at " + new Date() + '\n');

      // Listen for a connection request
      Socket socket = serverSocket.accept();
      jta.append("A new client is connected : " + socket + "\n"); 

   // Create data input and output streams
      DataInputStream inputFromClient = new DataInputStream(
        socket.getInputStream());
      DataOutputStream outputToClient = new DataOutputStream(
        socket.getOutputStream());
      
      System.out.println("Assigning new thread for this client"); 

      
      while (true) {
	        // Receive radius from the client
	        double radius = inputFromClient.readDouble();

	        // Compute area
	        double area = radius * radius * Math.PI;

	        // Send area back to the client
	        outputToClient.writeDouble(area);

	        jta.append("Radius received from " + Login.USERNAME + ": " + radius + '\n');			//TO-DO Grab USERNAME from the logged in user
	        jta.append("Area found: " + area + '\n');
		// create a new thread object 
		Thread t = new ClientHandler(serverSocket, inputFromClient, outputToClient); 

		// Invoking the start() method 
		t.start(); 
		
		
      
      
		
      }
    }
    catch(IOException ex) {
      System.err.println(ex);
    }
  }
}
