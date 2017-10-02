// Andrew Marrufo

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try {
			// Connect to a Server and get the two streams from the server
			System.out.println("Client started");
			Socket server = new Socket("localhost", 4000);
			System.out.println("This Client found a server on port 4000");

			// Do some IO with the server
			ObjectOutputStream output = new ObjectOutputStream(server.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(server.getInputStream());
			// Do some IO with the server
			
			Scanner keyboard = new Scanner(System.in);
			
			while(true) {
				System.out.print("Enter a message: ");
				String in = keyboard.next();
				if(in.toLowerCase().equals("quit")) {
					break;
				}
				output.writeObject(in);
				String messageFromServer = (String) input.readObject();
				System.out.println(messageFromServer);
			}
					
			output.writeObject("quit");
			
			// Close the connection to the server
			server.close();
			keyboard.close();
			System.out.println("You entered the magic word");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}