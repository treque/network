package Server;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		Scanner input = new Scanner(System.in);
		
		System.out.println("IP: ");
		String serverAddress = input.nextLine();
		// check with inet4 gethostaddress equals address
		
		System.out.println("PORT: ");
		int portNumber = Integer.parseInt(input.nextLine());
		
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