package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		while (true) {
			ServerSocket serverSocket = null;
			Socket socket = null;
			try {
				serverSocket = new ServerSocket(5000);
				socket = serverSocket.accept();
				
				Handler t = new Handler();
				t.start();		
			} finally {
				serverSocket.close();
				socket.close();
			}
		}
	}
}