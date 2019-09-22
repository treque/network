package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Paths;

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
		cmdReader = new CommandReader(new File(System.getProperty("user.dir")));
		try {
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String clientInput = in.readUTF();
			while (clientInput != "quit")
			{
				System.out.println("[" + socket + "]" + clientInput);
				dos.writeUTF(cmdReader.executor(clientInput));
				dos.flush();
				clientInput = in.readUTF();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
