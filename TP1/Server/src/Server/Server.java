package Server;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		while (true) {
			ServerSocket serverSocket = null;
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(5000);
				String serverAddress = "127.0.0.1";
				int serverPort = 5000;
				InetAddress serverIP = Inet4Address.getByName(serverAddress);
				serverSocket.bind(new InetSocketAddress(serverIP, serverPort));
				
				socket = serverSocket.accept();
				System.out.println(socket);
				
				Handler t = new Handler();
				t.start();		
			} finally {
				serverSocket.close();
				socket.close();
			}
		}
	}
}