package Server;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket serverSocket = null;
		Socket socket = null;
		//int serverPort = 5050; on doit le chagner a chaque fois parce que le finally est pas called.. en raison du while true.
		//String serverAddress = "127.0.0.1";
		serverSocket = new ServerSocket(5020);
		serverSocket.setReuseAddress(true);
		
		//InetAddress serverIP = Inet4Address.getByName(serverAddress);
		//serverSocket.bind(new InetSocketAddress(serverIP, serverPort));
		
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