import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

// ClientHandler class 
class ClientHandler extends Thread 
{ 
	
	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	final DataInputStream fromServer; 
	final DataOutputStream toServer; 
	final ServerSocket serverSocket; 
	
	
	

	// Constructor 
	public ClientHandler(ServerSocket serverSocket, DataInputStream fromServer, DataOutputStream toServer) 
	{ 
		this.serverSocket = serverSocket; 
		this.fromServer = fromServer; 
		this.toServer = toServer; 
	} 

	


public void run() {
	
	try {
		
		while(true) {
			
			//radius from user
			double radius = fromServer.readDouble();
	        // Compute area
	        double area = radius * radius * Math.PI;
	        // Send area back to the client
	        toServer.writeDouble(area);

		}
	} catch (Exception e) {
		System.err.println(e + "on" + serverSocket);
	}	
}
}
