// Andrew Marrufo

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			System.out.println("Server started");
			ServerSocket server = new ServerSocket(4000);
			Socket connection = server.accept();
			System.out.println("This Server just got a Client at port 4000");
			// Make both connection steams available
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(connection.getInputStream());
			// Do some IO.
			
			while(true) {
				String messageFromClient = (String) input.readObject();
				if(messageFromClient.equals("quit")) {
					break;
				}
				output.writeObject("Hi client, you wrote: " + messageFromClient);
				System.out.println("Hi client, you wrote: " + messageFromClient);		
			}
			
			// Close the connection
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}