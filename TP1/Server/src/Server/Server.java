package Server;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import Server.Validator;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		boolean isIPValid = false;
		boolean isPortValid = false;
		
		String serverAddress = null;
		int portNumber;
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("IP: \n");
			serverAddress = input.nextLine();
			isIPValid = Validator.isIPValid(serverAddress);
		} while (!isIPValid);
		do {
			System.out.println("PORT: \n");
			String inputString = input.nextLine();
			//verifier l'erreur avec le parse
			isPortValid = Validator.isPortValid(inputString);
			portNumber = Integer.parseInt(inputString);
		} while (!isPortValid);
		
		serverSocket = new ServerSocket(portNumber);
		serverSocket.setReuseAddress(true);

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
			if (socket != null)
			{
				socket.close();
			}
		}
	}
}