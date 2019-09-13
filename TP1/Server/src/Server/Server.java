package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Server.Validator;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		Scanner input = new Scanner(System.in);
		boolean isPortValid = false;
		boolean isHostValid = false;
		do {
			System.out.println("IP: ");
			String serverAddress = input.nextLine();
			// check with inet4 gethostaddress equals address
			isHostValid = Validator.isIPValid(serverAddress);
			System.out.println("PORT: ");
			
			isPortValid = Validator.isPortValid(input.nextLine());
			int portNumber = 0;
			String port = input.nextLine();
			isPortValid = Validator.isPortValid(port);
			
			if (isPortValid) 
			{
				portNumber = Integer.parseInt(port);
			}
			
			serverSocket = new ServerSocket(portNumber);
			
			serverSocket.setReuseAddress(true);
		}
		while(!isPortValid || !isHostValid);
		
		try 
		{
			while (true)
			{
				socket = serverSocket.accept();
				Handler t = new Handler(socket);
				t.start();
			}
		}
		finally 
		{
			serverSocket.close();
			input.close();
			if (socket != null)
			{
				socket.close();
			}
		}
	}
}
