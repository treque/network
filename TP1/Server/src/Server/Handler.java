package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler extends Thread {

	private Socket socket;
	public Handler(Socket socket)
	{
		this.socket = socket;
	}
	@Override
	public void run()
	{
		System.out.println(socket);
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			while (true)
			{
				System.out.println(in.readUTF());	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
