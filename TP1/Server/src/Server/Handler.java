package Server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Handler extends Thread {

	private Socket socket;
	public Handler()
	{
	
	}
	@Override
	public void run()
	{
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			System.out.println(in.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
