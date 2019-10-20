package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import Server.Commands.CommandReader;

public class Handler extends Thread {

	private Socket socket;
	private CommandReader cmdReader;
	
	public Handler(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
		System.out.println("Socket: " + socket + " connection established");
		cmdReader = new CommandReader(new File(System.getProperty("user.dir")), socket);
		DataInputStream in = null;
		DataOutputStream dos = null;
		
		SimpleDateFormat dateFormatter= new SimpleDateFormat(" yyyy-MM-dd'@'HH:mm:ss");
		
		try {
			in = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			String clientInput = "abc";
			
			while (!clientInput.equals("exit"))
			{
				clientInput = in.readUTF();
				Date date = new Date(System.currentTimeMillis());
				System.out.println("[" + socket.getInetAddress().toString().substring(1) + ":" + socket.getPort() + dateFormatter.format(date) + "]: " + clientInput); // a format
				String serverResponse = cmdReader.executor(clientInput);
				System.out.println("REPONSE"+serverResponse);
				if (serverResponse != "")
				{
					dos.writeUTF(serverResponse);
					dos.flush();					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in!=null) in.close();
				if (dos!=null) dos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

			
		}
	}
}
